package com.taylor.stock.request;

import com.taylor.api.ApiClient;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockData;
import com.taylor.entity.stock.HistoryData;
import com.taylor.entity.stock.StockFundInOut;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.service.RecmdStockService;
import com.taylor.stock.common.OperatorEnum;
import com.taylor.stock.strategy.IStrategy;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.List;


/**
 * 获取股票日K数据
 *
 * @author taylor
 */
@Slf4j
public class QueryStockDayDataRequest extends Thread {

    private RecmdStockService recmdStockService;

    private List<StockData> stockDataList;

    private IStrategy strategy;


    public static int run_flag = 1;

    public QueryStockDayDataRequest(IStrategy strategy, RecmdStockService recmdStockService, List<StockData> stockDataList, String taskName) {
        super(taskName);
        this.recmdStockService = recmdStockService;
        this.stockDataList = stockDataList;
        this.strategy = strategy;
    }

    @Override
    public void run() {
        DecimalFormat df = new DecimalFormat("######0.000");
        for (StockData stockData : stockDataList) {
            if (run_flag == 0) {
                break;
            }

            log.info("正在检测股票：{}", stockData.getStockName());
            StockPanKouData panKouData = ApiClient.getPanKouData(stockData.getStockCode());
            List<HistoryData> historyData = ApiClient.getHistoryData(stockData.getStockCode().toLowerCase(), 10);
            HistoryData today = historyData.get(historyData.size() - 1);
            HistoryData yestoday = historyData.get(historyData.size() - 2);
            IStrategy temp = strategy;
            /**去掉停牌的股票**/
            if (panKouData.getLiangBi() == 0) {
                continue;
            }
            StockFundInOut stockFundInOutData = ApiClient.getStockFundInOutData(stockData.getStockCode());
            while (strategy != null && run_flag == 1) {
                int checkResult = strategy.doCheck(historyData, stockData.getStockCode().toLowerCase());
                if (checkResult == 1 && panKouData != null) {
                    RecmdStock recmdStock = new RecmdStock()
                            .setStockCode(stockData.getStockCode())
                            .setTurnoverRatio(panKouData.getExchangeRatio())
                            .setStockName(panKouData.getStockName())
                            .setRecordPrice(today.getClose())
                            .setCurrentPrice(today.getClose())
                            .setStrategy(strategy.getStrategyEnum().getDesc())
                            .setStrategyType(strategy.getStrategyEnum().getCode())
                            .setMainIn(stockFundInOutData == null ? null : stockFundInOutData.getMainTotalIn())
                            .setMainInBi(stockFundInOutData == null ? null : stockFundInOutData.getMainInBi())
                            .setRecmdOperate(OperatorEnum.OPERATOR_ENUM_MAP.get(checkResult))
                            .setLiangbi(panKouData.getLiangBi())
                            .setChangeRatioYestoday((today.getClose() - yestoday.getClose()) / yestoday.getClose() * 100)
                            .setOuterPan(panKouData.getOuter())
                            .setInnerPan(panKouData.getInner());
                    recmdStockService.save(recmdStock);
                    log.info("股票代码：{}中标策略:{}", stockData.getStockCode(), strategy.getStrategyEnum().getDesc());
                }
                strategy = strategy.getNext();
            }
            strategy = temp;
        }
        System.out.println("##############################线程：" + Thread.currentThread().getName() + "已执行完毕###############################");

    }
}