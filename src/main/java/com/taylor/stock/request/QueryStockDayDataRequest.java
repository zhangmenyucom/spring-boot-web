package com.taylor.stock.request;

import com.taylor.common.CommonRequest;
import com.taylor.common.Constants;
import com.taylor.common.JsonUtil;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockBaseInfo;
import com.taylor.entity.StockBusinessinfo;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.MashDataResponse;
import com.taylor.entity.stock.StockFundInOut;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.entity.stock.query.StockQueryBean;
import com.taylor.service.RecmdStockService;
import com.taylor.stock.common.OperatorEnum;
import com.taylor.stock.strategy.IStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.methods.GetMethod;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.taylor.common.Constants.METHOD_URL_STOCK_DAY_INFO;

/**
 * 获取股票日K数据
 *
 * @author taylor
 */
@Slf4j
public class QueryStockDayDataRequest extends Thread {

    private RecmdStockService recmdStockService;

    private List<String> stockCodeList;

    private IStrategy strategy;

    private Integer count;

    public static int run_flag = 1;

    public QueryStockDayDataRequest(IStrategy strategy, RecmdStockService recmdStockService, List<String> stockCodeList, String taskName, Integer count) {
        super(taskName);
        this.recmdStockService = recmdStockService;
        this.stockCodeList = stockCodeList;
        this.strategy = strategy;
        this.count = count;
    }

    public static String queryLatestResult(StockQueryBean stockQueryBean, GetMethod method) {
        return new CommonRequest<StockQueryBean>().executeRequest(stockQueryBean, method);
    }

    @Override
    public void run() {
        StockQueryBean stockQueryBean = new StockQueryBean();
        stockQueryBean.setFrom("pc");
        stockQueryBean.setCount(count + "");
        stockQueryBean.setCuid("xxx");
        stockQueryBean.setFormat("json");
        stockQueryBean.setFq_type("no");
        stockQueryBean.setStep(1 + "");
        stockQueryBean.setOs_ver("1");
        stockQueryBean.setVv("100");
        DecimalFormat df = new DecimalFormat("######0.000");

        GetMethod methodKline = new GetMethod(METHOD_URL_STOCK_DAY_INFO);
        GetMethod methodBase = new GetMethod(Constants.METHOD_URL_STOCK_BASE_INFO);
        for (String stockCode : stockCodeList) {
            if (run_flag == 0) {
                break;
            }
            stockQueryBean.setStock_code(stockCode.toLowerCase());
            log.info("正在检测股票代码：{}", stockCode);
            String responseStr = queryLatestResult(stockQueryBean, methodKline);
            if (responseStr == null) {
                continue;
            }
            List<StockBaseInfo> stockBaseInfos = QueryStockBaseDataRequest.queryStockBaseInfo(stockCode.toLowerCase(), methodBase);
            /**停牌的过滤掉**/
            if (stockBaseInfos == null || stockBaseInfos.isEmpty() || "0".equals(stockBaseInfos.get(0).getStockStatus())) {
                continue;
            }
            MashDataResponse response = JsonUtil.transferToObj(responseStr, MashDataResponse.class);
            if (response == null || response.getErrorNo() != 0) {
                continue;
            }
            if(response.getMashData()==null || response.getMashData().isEmpty()){
                response.setMashData(new ArrayList<MashData>());
                response.getMashData().add(new MashData());
            }
            MashData mashDataToday = response.getMashData().get(0);
            mashDataToday.setStockCode(stockCode.toLowerCase());
            List<MashData> mashDataList = new ArrayList<>();
            for (MashData mashData : response.getMashData()) {
                mashDataList.add(mashData);
            }
            IStrategy temp = strategy;
            while (strategy != null && run_flag == 1) {
                int checkResult = strategy.doCheck(mashDataList);
                StockFundInOut stockFundInOutData = CommonRequest.getStockFundInOutData(stockCode);
                StockBusinessinfo stockBusinessinfo = QueryStockBusinessDataRequest.queryStockBasicBussinessInfo(stockCode);
                /**只查买入意见的股票**/
                StockPanKouData stockPanKouData = CommonRequest.getStockPanKouData(stockCode);
                if (checkResult == 1 && stockPanKouData != null) {
                    RecmdStock recmdStock = new RecmdStock();
                    recmdStock.setStockCode(stockCode);
                    recmdStock.setTurnoverRatio(stockPanKouData.getExchangeRatio());
                    recmdStock.setStockName(stockPanKouData.getStockName());
                    recmdStock.setRecordPrice(Double.valueOf(df.format(stockBaseInfos.get(0).getClose())));
                    recmdStock.setCurrentPrice(Double.valueOf(df.format(stockBaseInfos.get(0).getClose())));
                    recmdStock.setStrategy(strategy.getStrategyEnum().getDesc());
                    recmdStock.setStrategyType(strategy.getStrategyEnum().getCode());
                    recmdStock.setMainIn(stockFundInOutData == null ? null : stockFundInOutData.getMainTotalIn());
                    recmdStock.setMainInBi(stockFundInOutData == null ? null : stockFundInOutData.getMainInBi());
                    recmdStock.setRecmdOperate(OperatorEnum.OPERATOR_ENUM_MAP.get(checkResult));
                    recmdStock.setLiangbi(stockPanKouData.getLiangBi());
                    recmdStock.setChangeRatioYestoday(stockBaseInfos.get(0).getNetChangeRatio());
                    recmdStock.setOuterPan(stockPanKouData.getOuter());
                    recmdStock.setInnerPan(stockPanKouData.getInner());
                    recmdStock.setKdjCount(mashDataList.get(0).getKdjCount());
                    recmdStock.setIndustry(stockBusinessinfo.getIndustry());
                    recmdStock.setMajoGrow(stockBusinessinfo.getMajoGrow());
                    recmdStock.setNetIncreaseRate(stockBusinessinfo.getNetIncreaseRate());
                    recmdStockService.save(recmdStock);
                    log.info("股票代码：{}中标策略:{}", stockCode, strategy.getStrategyEnum().getDesc());
                }
                strategy = strategy.getNext();
            }
            strategy = temp;
        }
        System.out.println("##############################线程：" + Thread.currentThread().getName() + "已执行完毕###############################");
        if (!methodKline.isAborted()) {
            methodKline.releaseConnection();
        }
    }

    public static void main(String... args) {
        StockQueryBean stockQueryBean = new StockQueryBean();
        stockQueryBean.setFrom("pc");
        stockQueryBean.setCount(5 + "");
        stockQueryBean.setCuid("xxx");
        stockQueryBean.setFormat("json");
        stockQueryBean.setFq_type("no");
        stockQueryBean.setOs_ver("1");
        stockQueryBean.setVv("100");
        stockQueryBean.setStock_code("SZ300615".toLowerCase());
        GetMethod method = new GetMethod(METHOD_URL_STOCK_DAY_INFO);
        System.out.println(queryLatestResult(stockQueryBean, method));

    }
}