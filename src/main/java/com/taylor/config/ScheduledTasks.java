package com.taylor.config;

import com.taylor.common.Constants;
import com.taylor.common.StockUtils;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockBaseInfo;
import com.taylor.entity.StockOnShelf;
import com.taylor.entity.stock.GuZhenResponse;
import com.taylor.service.RecmdStockService;
import com.taylor.service.StockDataService;
import com.taylor.service.StockOnShelfService;
import com.taylor.stock.request.GuZhenRequest;
import com.taylor.stock.request.QueryStockBaseDataRequest;
import com.taylor.stock.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author taylor
 */
@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    private StockDataService stockDataService;

    @Autowired
    private RecmdStockService recmdStockService;

    @Autowired
    private StockOnShelfService stockOnShelfService;

    /**
     * 每分钟刷新推荐数据
     */
   // @Scheduled(cron = "*/60 * * * * *")
    public void updateRecmdData() {
        if (!StockUtils.noNeedMonotorTime()) {
            HttpMethodBase method = new GetMethod(Constants.METHOD_URL_STOCK_BASE_INFO);
            List<RecmdStock> recmdStocks = recmdStockService.find(new RecmdStock());
            log.info("正在刷新推荐股票数据...........");
            for (RecmdStock recmdStock : recmdStocks) {
                List<StockBaseInfo> stockBaseInfos = QueryStockBaseDataRequest.queryStockBaseInfo(recmdStock.getStockCode(), method);
                if (!stockBaseInfos.isEmpty()) {
                    stockBaseInfos.get(0).setId(recmdStock.getId());
                    recmdStockService.updateUpDownRatio(stockBaseInfos.get(0));
                }
            }
        }
    }

    /**
     * 每30秒刷新股架数据
     */
    //@Scheduled(cron = "*/30 * * * * *")
    public void updateShelfData() {
        if (!StockUtils.noNeedMonotorTime()) {
            log.info("正在刷新股架数据...........");
            StockOnShelf stockOnShelfUpdate = new StockOnShelf();
            stockOnShelfService.updateSelf(stockOnShelfUpdate);
        }
    }

    /**
     * 每天定时刷新推荐数据
     */
   // @Scheduled(cron = "0 51 17 * * *")
    public void fetchRecmdData() {
        RecmdStock recmdStockDel = new RecmdStock();
        /**清空数据**/
        recmdStockService.del(recmdStockDel);

        Kdj5Strategy kdj5Strategy = new Kdj5Strategy();
        Kdj10Strategy kdj10Strategy = new Kdj10Strategy();
        kdj5Strategy.setNext(kdj10Strategy);
        Kdj510Strategy kdj510Strategy = new Kdj510Strategy();
        kdj10Strategy.setNext(kdj510Strategy);
        KdjDayWeekMonthXStrategy kdjDayWeekMonthXStrategy = new KdjDayWeekMonthXStrategy();
        kdj510Strategy.setNext(kdjDayWeekMonthXStrategy);
        KdjOver45duRatioStrategy kdjOver45duRatioStrategy = new KdjOver45duRatioStrategy(5.0f, 0.9f);
        kdjDayWeekMonthXStrategy.setNext(kdjOver45duRatioStrategy);
        KdjOverWithRatioStrategy kdjOverWithRatioStrategy = new KdjOverWithRatioStrategy(5.0f, 0.9f);
        kdjOver45duRatioStrategy.setNext(kdjOverWithRatioStrategy);
        KdjWeekRatioStrategy kdjWeekRatioStrategy = new KdjWeekRatioStrategy(0.9f);
        kdjOverWithRatioStrategy.setNext(kdjWeekRatioStrategy);
        KdjWeekRatioStrategyLiangBiMianInOut kdjWeekRatioStrategyLiangBiMianInOut = new KdjWeekRatioStrategyLiangBiMianInOut(0.9f);
        kdjWeekRatioStrategy.setNext(kdjWeekRatioStrategyLiangBiMianInOut);
        KdjWeekRatioStrategyLiangBiOver1 kdjWeekRatioStrategyLiangBiOver1 = new KdjWeekRatioStrategyLiangBiOver1(0.9f);
        kdjWeekRatioStrategyLiangBiMianInOut.setNext(kdjWeekRatioStrategyLiangBiOver1);
        KdjWithRatioStrategy kdjWithRatioStrategy = new KdjWithRatioStrategy(5.0f, 0.9f);
        kdjWeekRatioStrategyLiangBiOver1.setNext(kdjWithRatioStrategy);
        MacdStrategy macdStrategy = new MacdStrategy();
        kdjWithRatioStrategy.setNext(macdStrategy);
        ShiZiMacdStrategy shiZiMacdStrategy = new ShiZiMacdStrategy();
        macdStrategy.setNext(shiZiMacdStrategy);
        TMacdStrategy tMacdStrategy = new TMacdStrategy();
        macdStrategy.setNext(tMacdStrategy);
        Over5DayStrategy over5DayStrategy = new Over5DayStrategy();
        tMacdStrategy.setNext(over5DayStrategy);
        Over10DayStrategy over10DayStrategy = new Over10DayStrategy();
        over5DayStrategy.setNext(over10DayStrategy);
        Over20DayStrategy over20DayStrategy = new Over20DayStrategy();
        over10DayStrategy.setNext(over20DayStrategy);
        stockDataService.processData(kdj5Strategy);
    }

    /**
     * 每天定时刷新推荐数据
     */
    //@Scheduled(cron = "0 50 16 * * *")
    public void fetchRecmdforGodenCountData() {
        stockDataService.processData(new GodenKdjCountStrategy(), 80);
    }

    /**
     * 每天定时刷新天鹅拳形态数据
     */
    //@Scheduled(cron = "0 55 16 * * *")
    public void fetchTianEQuanData() {
        stockDataService.processData(new TianEQuanStrategy(), 13);
    }

    /**
     * 每天定时刷新推荐股诊数据
     */
    //@Scheduled(cron = "0 28 17 * * *")
    public void updateGuZhengData() throws InterruptedException {
        List<RecmdStock> recmdStocks = recmdStockService.find(new RecmdStock());
        log.info("正在刷新推荐股票数据...........");
        for (RecmdStock recmdStock : recmdStocks) {
            GuZhenResponse guZhengData = GuZhenRequest.getGuZhengData(recmdStock.getStockCode());
            if (guZhengData != null) {
                RecmdStock recmdStockUpdate = new RecmdStock();
                recmdStockUpdate.setStockCode(recmdStock.getStockCode());
                recmdStockUpdate.setScore(guZhengData.getData().getData().getResult().get_score());
                recmdStockService.updateGuZhenScore(recmdStockUpdate);
            }
            Thread.sleep(5000);
        }
    }

}