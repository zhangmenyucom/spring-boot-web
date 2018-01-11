package com.taylor.stock.request;

import com.taylor.common.CommonRequest;
import com.taylor.common.Constants;
import com.taylor.common.JsonUtil;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.MashDataResponse;
import com.taylor.entity.StockBaseInfo;
import com.taylor.entity.stock.query.StockQueryBean;
import com.taylor.service.RecmdStockService;
import com.taylor.service.impl.RedisServiceImpl;
import com.taylor.stock.common.OperatorEnum;
import com.taylor.stock.common.StockStatusEnum;
import com.taylor.stock.strategy.IStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.methods.GetMethod;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.taylor.common.ProcessCountor.CURRENT;

/**
 * 获取股票数据
 *
 * @author taylor
 */
@Slf4j
public class QueryStockTodayDataRequest extends Thread {

    private RecmdStockService recmdStockService;

    private List<String> stockCodeList;

    private RedisServiceImpl<String> redisService;

    private IStrategy strategy;

    public QueryStockTodayDataRequest(IStrategy strategy, RedisServiceImpl<String> redisService, RecmdStockService recmdStockService, List<String> stockCodeList, String taskName) {
        super(taskName);
        this.recmdStockService = recmdStockService;
        this.stockCodeList = stockCodeList;
        this.redisService = redisService;
        this.strategy = strategy;
    }

    public static String queryLatestResult(StockQueryBean stockQueryBean, GetMethod method) {
        return new CommonRequest<StockQueryBean>().executeRequest(stockQueryBean, method);
    }

    @Override
    public void run() {
        StockQueryBean stockQueryBean = new StockQueryBean();
        stockQueryBean.setFrom("pc");
        stockQueryBean.setCount(2 + "");
        stockQueryBean.setCuid("xxx");
        stockQueryBean.setFormat("json");
        stockQueryBean.setFq_type("no");
        stockQueryBean.setStep(1 + "");
        stockQueryBean.setOs_ver("1");
        stockQueryBean.setVv("100");
        DecimalFormat df = new DecimalFormat("######0.000");

        GetMethod methodKline = new GetMethod(Constants.METHOD_URL_STOCK_DAY_INFO);
        GetMethod methodBase = new GetMethod(Constants.METHOD_URL_STOCK_BASE_INFO);
        for (String stockCode : stockCodeList) {
            CURRENT.incrementAndGet();
            stockQueryBean.setStock_code(stockCode.toLowerCase());
            System.out.println("正在检测股票代码：" + stockCode);
            String responseStr = queryLatestResult(stockQueryBean, methodKline);
            if (responseStr == null) {
                continue;
            }
            MashDataResponse response = JsonUtil.transferToObj(responseStr, MashDataResponse.class);
            if (response == null || response.getErrorNo() != 0 || response.getMashData() == null || response.getMashData().size() < 2) {
                continue;
            }
            MashData mashDataToday = response.getMashData().get(0);
            mashDataToday.setBlockCode(stockCode.toLowerCase());
            List<MashData> mashDataList = new ArrayList<>();
            for (MashData mashData : response.getMashData()) {
                mashDataList.add(mashData);
            }
            int checkResult = strategy.doCheck(mashDataList);
            /**只查买入意见的股票**/
            if (checkResult == 1) {
                RecmdStock recmdStock = new RecmdStock();
                List<StockBaseInfo> stockBaseInfos = QueryStockBaseDataRequest.queryStockBaseInfo(stockCode, methodBase);
                if(!stockBaseInfos.isEmpty()){
                    /**忽略停牌**/
                    if(stockBaseInfos.get(0).getStockStatus().equals(StockStatusEnum.STOP.getCode().toString())){
                        continue;
                    }
                    recmdStock.setTurnoverRatio(Double.valueOf(df.format(stockBaseInfos.get(0).getTurnoverRatio())));
                }
                recmdStock.setMacd(Double.valueOf(df.format(mashDataToday.getMacd().getMacd())));
                recmdStock.setStockCode(stockCode);
                recmdStock.setStockName(redisService.get(stockCode));
                recmdStock.setCurrentPrice(Double.valueOf(df.format(mashDataToday.getKline().getClose())));
                recmdStock.setStrategy(strategy.getName());
                recmdStock.setKdj("(" + (int) mashDataToday.getKdj().getK() + "," + (int) mashDataToday.getKdj().getD() + "," + (int) mashDataToday.getKdj().getJ() + ")");
                recmdStock.setRecmdOperate(OperatorEnum.OPERATOR_ENUM_MAP.get(checkResult));
                recmdStockService.save(recmdStock);
                log.info("股票代码：{}中标macd:{}", stockCode, response.getMashData().get(0).getMacd().getMacd());
                System.out.println(stockCode + "中标:" + response.getMashData().get(0).getMacd().getMacd());
            }
        }
        System.out.println("##############################线程：" + Thread.currentThread().getName() + "已执行完毕###############################");
        if (!methodKline.isAborted()) {
            methodKline.releaseConnection();
        }
    }

    public static void main(String... args) {
        StockQueryBean stockQueryBean = new StockQueryBean();
        stockQueryBean.setFrom("pc");
        stockQueryBean.setCount(2 + "");
        stockQueryBean.setCuid("xxx");
        stockQueryBean.setFormat("json");
        stockQueryBean.setFq_type("no");
        stockQueryBean.setOs_ver("1");
        stockQueryBean.setVv("100");
        stockQueryBean.setStock_code("SZ002186".toLowerCase());
        GetMethod method=new GetMethod(Constants.METHOD_URL_STOCK_DAY_INFO);
        System.out.println( queryLatestResult(stockQueryBean,method));

    }
}