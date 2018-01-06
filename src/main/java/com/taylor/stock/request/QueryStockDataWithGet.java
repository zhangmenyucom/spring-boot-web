package com.taylor.stock.request;

import com.taylor.common.CommonResponse;
import com.taylor.common.JsonUtil;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.StockQueryBean;
import com.taylor.service.RecmdStockService;
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
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.taylor.stock.request.ProcessCountor.CURRENT;

/**
 * 获取股票数据
 *
 * @author taylor
 */
@Slf4j
public class QueryStockDataWithGet extends Thread {

    private RecmdStockService recmdStockService;

    private List<String> stockCodeList;

    public QueryStockDataWithGet(RecmdStockService recmdStockService, List<String> stockCodeList) {
        this.recmdStockService = recmdStockService;
        this.stockCodeList = stockCodeList;

    }

    public static CommonResponse queryLatestResult(StockQueryBean stockQueryBean, GetMethod method) throws IOException {
        HttpClient client = new HttpClient();
        NameValuePair[] data = {
                new NameValuePair("from", "pc"),
                new NameValuePair("os_ver", stockQueryBean.getOs_ver()),
                new NameValuePair("cuid", stockQueryBean.getCuid()),
                new NameValuePair("vv", "100"),
                new NameValuePair("format", "json"),
                new NameValuePair("stock_code", stockQueryBean.getStock_code()),
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
            return JsonUtil.transferToObj(stringBuider.toString(), CommonResponse.class);
        }
        return null;
    }

    @Override
    public void run() {
        StockQueryBean stockQueryBean = new StockQueryBean();
        stockQueryBean.setFrom("pc");
        stockQueryBean.setCount(1 + "");
        stockQueryBean.setCuid("xxx");
        stockQueryBean.setFormat("json");
        stockQueryBean.setFq_type("no");
        stockQueryBean.setStep(2 + "");

        GetMethod method = new GetMethod("https://gupiao.baidu.com/api/stocks/stockdaybar");
        for (String s : stockCodeList) {
            CURRENT.addAndGet(1);
            stockQueryBean.setStock_code(s.toLowerCase());
            System.out.println("正在检测股票代码：" + s);
            CommonResponse stockDailyDataCommonResponse = null;
            try {
                stockDailyDataCommonResponse = queryLatestResult(stockQueryBean, method);
                if (stockDailyDataCommonResponse == null || stockDailyDataCommonResponse.getErrorNo() != 0 || stockDailyDataCommonResponse.getMashData() == null || stockDailyDataCommonResponse.getMashData().isEmpty()) {
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (stockDailyDataCommonResponse.getMashData().get(0).getMacd().getMacd() > 0 && stockDailyDataCommonResponse.getMashData().get(0).getMacd().getMacd() <= 0.01) {
                RecmdStock recmdStock = new RecmdStock();
                MashData mashData = stockDailyDataCommonResponse.getMashData().get(0);
                recmdStock.setMacd(mashData.getMacd().getMacd());
                recmdStock.setStockCode(s);
                recmdStock.setKdj("("+mashData.getKdj().getK()+","+mashData.getKdj().getD()+","+mashData.getKdj().getJ()+")");
                recmdStockService.save(recmdStock);
                log.info("股票代码：{}中标macd:{}", s, stockDailyDataCommonResponse.getMashData().get(0).getMacd().getMacd());
                System.out.println(s + "中标:" + stockDailyDataCommonResponse.getMashData().get(0).getMacd().getMacd());
            }
        }
        if (!method.isAborted()) {
            method.releaseConnection();
        }
    }
}