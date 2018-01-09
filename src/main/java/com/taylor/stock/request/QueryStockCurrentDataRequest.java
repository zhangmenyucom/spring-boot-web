package com.taylor.stock.request;

import com.taylor.common.JsonUtil;
import com.taylor.common.MashDataResponse;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.StockQueryBean;
import com.taylor.service.RecmdStockService;
import com.taylor.service.impl.RedisServiceImpl;
import com.taylor.stock.common.OperatorEnum;
import com.taylor.stock.strategy.IStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import tk.mybatis.mapper.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.taylor.stock.request.ProcessCountor.CURRENT;

/**
 * 获取股票数据
 *
 * @author taylor
 */
@Slf4j
public class QueryStockCurrentDataRequest extends Thread {

    private RecmdStockService recmdStockService;

    private List<String> stockCodeList;

    private RedisServiceImpl<String> redisService;

    private IStrategy strategy;

    public QueryStockCurrentDataRequest(IStrategy strategy, RedisServiceImpl<String> redisService, RecmdStockService recmdStockService, List<String> stockCodeList, String taskName) {
        super(taskName);
        this.recmdStockService = recmdStockService;
        this.stockCodeList = stockCodeList;
        this.redisService = redisService;
        this.strategy = strategy;
    }

    public static MashDataResponse queryLatestResult(StockQueryBean stockQueryBean, GetMethod method) throws IOException {
        HttpClient client = new HttpClient();
        NameValuePair[] data = {
                new NameValuePair("from", "pc"),
                new NameValuePair("os_ver", stockQueryBean.getOs_ver()),
                new NameValuePair("cuid", stockQueryBean.getCuid()),
                new NameValuePair("vv", "100"),
                new NameValuePair("format", "json"),
                new NameValuePair("stock_code", stockQueryBean.getStockCode()),
                new NameValuePair("step", stockQueryBean.getStep()),
                new NameValuePair("start", stockQueryBean.getStart()),
                new NameValuePair("count", stockQueryBean.getCount()),
                new NameValuePair("fq_type", stockQueryBean.getFq_type()),
                new NameValuePair("timestamp", Long.valueOf(new Date().getTime()).toString())
        };
        // method使用表单阈值
        method.setQueryString(data);
        // 提交表单
        client.executeMethod(method);
        // 字符流转字节流 循环输出，同get解释
        InputStream inputStream = method.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder stringBuider = new StringBuilder();
        String str;
        while ((str = br.readLine()) != null) {
            stringBuider.append(str);
        }
        if (!StringUtil.isEmpty(stringBuider.toString())) {
            return JsonUtil.transferToObj(stringBuider.toString(), MashDataResponse.class);
        }
        return null;
    }

    @Override
    public void run() {
        StockQueryBean stockQueryBean = new StockQueryBean();
        stockQueryBean.setFrom("pc");
        stockQueryBean.setCount(2 + "");
        stockQueryBean.setCuid("xxx");
        stockQueryBean.setFormat("json");
        stockQueryBean.setFq_type("no");
        stockQueryBean.setStep(2 + "");
        DecimalFormat df = new DecimalFormat("######0.000");

        GetMethod method = new GetMethod("https://gupiao.baidu.com/api/stocks/stockdaybar");
        for (String s : stockCodeList) {
            CURRENT.incrementAndGet();
            stockQueryBean.setStockCode(s.toLowerCase());
            System.out.println("正在检测股票代码：" + s);
            MashDataResponse stockDailyDataCommonResponse = null;
            try {
                stockDailyDataCommonResponse = queryLatestResult(stockQueryBean, method);
                if (stockDailyDataCommonResponse == null || stockDailyDataCommonResponse.getErrorNo() != 0 || stockDailyDataCommonResponse.getMashData() == null || stockDailyDataCommonResponse.getMashData().size() < 2) {
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<MashData> mashDataList = new ArrayList<>();
            MashData mashDataToday = stockDailyDataCommonResponse.getMashData().get(0);
            for (MashData mashData : stockDailyDataCommonResponse.getMashData()) {
                mashDataList.add(mashData);
            }
            int checkResult = strategy.doCheck(mashDataList);
            if (checkResult != 0) {
                RecmdStock recmdStock = new RecmdStock();
                recmdStock.setMacd(Double.valueOf(df.format(mashDataToday.getMacd().getMacd())));
                recmdStock.setStockCode(s);
                recmdStock.setStockName(redisService.get(s));
                recmdStock.setCurrentPrice(Double.valueOf(df.format(mashDataToday.getKline().getClose())));
                recmdStock.setStrategy(strategy.getName());
                recmdStock.setKdj("(" + (int) mashDataToday.getKdj().getK() + "," + (int) mashDataToday.getKdj().getD() + "," + (int) mashDataToday.getKdj().getJ() + ")");
                recmdStock.setRecmdOperate(OperatorEnum.OPERATOR_ENUM_MAP.get(checkResult));
                recmdStockService.save(recmdStock);
                log.info("股票代码：{}中标macd:{}", s, stockDailyDataCommonResponse.getMashData().get(0).getMacd().getMacd());
                System.out.println(s + "中标:" + stockDailyDataCommonResponse.getMashData().get(0).getMacd().getMacd());
            }
        }
        System.out.println("##############################线程：" + Thread.currentThread().getName() + "已执行完毕###############################");
        if (!method.isAborted()) {
            method.releaseConnection();
        }
    }
}