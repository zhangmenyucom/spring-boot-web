package com.taylor.stock.request;

import com.taylor.api.ApiClient;
import com.taylor.common.JsonUtil;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockBusinessinfo;
import com.taylor.entity.stock.HistoryData;
import com.taylor.entity.stock.StockFundInOut;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.service.RecmdStockService;
import com.taylor.stock.common.OperatorEnum;
import com.taylor.stock.strategy.IStrategy;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.Executors;


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


    public static int run_flag = 1;

    public QueryStockDayDataRequest(IStrategy strategy, RecmdStockService recmdStockService, List<String> stockCodeList, String taskName) {
        super(taskName);
        this.recmdStockService = recmdStockService;
        this.stockCodeList = stockCodeList;
        this.strategy = strategy;
    }

    @Override
    public void run() {
        DecimalFormat df = new DecimalFormat("######0.000");
        for (String stockCode : stockCodeList) {
            if (run_flag == 0) {
                break;
            }

            log.info("正在检测股票代码：{}", stockCode);
            StockPanKouData panKouData = ApiClient.getPanKouData(stockCode);
            List<HistoryData> historyData = ApiClient.getHistoryData(stockCode.toLowerCase(), 10);
            HistoryData today = historyData.get(historyData.size()-1);
            HistoryData yestoday = historyData.get(historyData.size()-2);
            IStrategy temp = strategy;
            /**去掉停牌的股票**/
            if(panKouData.getLiangBi()==0){
                continue;
            }
            StockFundInOut stockFundInOutData =ApiClient.getStockFundInOutData(stockCode);
            StockBusinessinfo stockBusinessinfo =ApiClient.queryStockBasicBussinessInfo(stockCode);
            while (strategy != null && run_flag == 1) {
                int checkResult = strategy.doCheck(historyData,stockCode.toLowerCase());
                if (checkResult == 1 && panKouData != null) {
                    RecmdStock recmdStock = new RecmdStock();
                    recmdStock.setStockCode(stockCode);
                    recmdStock.setTurnoverRatio(panKouData.getExchangeRatio());
                    recmdStock.setStockName(panKouData.getStockName());
                    recmdStock.setRecordPrice(today.getClose());
                    recmdStock.setCurrentPrice(today.getClose());
                    recmdStock.setStrategy(strategy.getStrategyEnum().getDesc());
                    recmdStock.setStrategyType(strategy.getStrategyEnum().getCode());
                    recmdStock.setMainIn(stockFundInOutData == null ? null : stockFundInOutData.getMainTotalIn());
                    recmdStock.setMainInBi(stockFundInOutData == null ? null : stockFundInOutData.getMainInBi());
                    recmdStock.setRecmdOperate(OperatorEnum.OPERATOR_ENUM_MAP.get(checkResult));
                    recmdStock.setLiangbi(panKouData.getLiangBi());
                    recmdStock.setChangeRatioYestoday((today.getClose()-yestoday.getClose())/yestoday.getClose()*100);
                    recmdStock.setOuterPan(panKouData.getOuter());
                    recmdStock.setInnerPan(panKouData.getInner());
                    recmdStock.setMajoGrow(stockBusinessinfo.getMajoGrow());
                    recmdStock.setNetIncreaseRate(stockBusinessinfo.getNetIncreaseRate());
                    recmdStockService.save(recmdStock);
                    Executors.newFixedThreadPool(1);
                    log.info("股票代码：{}中标策略:{}", stockCode, strategy.getStrategyEnum().getDesc());
                }
                strategy = strategy.getNext();
            }
            strategy = temp;
        }
        System.out.println("##############################线程：" + Thread.currentThread().getName() + "已执行完毕###############################");

    }

    public static void main(String... args) {
        System.out.println(JsonUtil.transfer2JsonString(ApiClient.getLatestResult(10,"sz300615")));
    }
}