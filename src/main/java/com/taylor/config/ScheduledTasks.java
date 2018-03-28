package com.taylor.config;

import com.taylor.common.Constants;
import com.taylor.common.StockUtils;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockBaseInfo;
import com.taylor.entity.StockOnShelf;
import com.taylor.service.RecmdStockService;
import com.taylor.service.StockDataService;
import com.taylor.service.StockOnShelfService;
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
    @Scheduled(cron = "*/60 * * * * *")
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
    @Scheduled(cron = "*/30 * * * * *")
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
    @Scheduled(cron = "0 18 22 * * *")
    public void fetchRecmdData() {
        RecmdStock recmdStockDel = new RecmdStock();
        ShiZiMacdStrategy shiZiMacdStrategy = new ShiZiMacdStrategy();
        TMacdStrategy tMacdStrategy = new TMacdStrategy();
        shiZiMacdStrategy.setNext(tMacdStrategy);
        Over5DayStrategy over5DayStrategy = new Over5DayStrategy();
        tMacdStrategy.setNext(over5DayStrategy);
        Over10DayStrategy over10DayStrategy = new Over10DayStrategy();
        over5DayStrategy.setNext(over10DayStrategy);
        Over20DayStrategy over20DayStrategy = new Over20DayStrategy();
        over10DayStrategy.setNext(over20DayStrategy);
        BigYinLineStrategy bigYinLineStrategy = new BigYinLineStrategy();
        over20DayStrategy.setNext(bigYinLineStrategy);
        BeiLiStrategy beiLiStrategy = new BeiLiStrategy();
        bigYinLineStrategy.setNext(beiLiStrategy);
        stockDataService.processData(shiZiMacdStrategy);
    }

    /**
     * 每天定时刷新推荐数据
     */
    @Scheduled(cron = "0 7 18 * * *")
    public void fetchRecmdforGodenCountData() {
        stockDataService.processData(new GodenKdjCountStrategy(), 80);
    }

    /**
     * 每天定时刷新天鹅拳形态数据
     */
    @Scheduled(cron = "0 10 18 * * *")
    public void fetchTianEQuanData() {
        stockDataService.processData(new TianEQuanStrategy(), 13);
    }

    /**
     * 尾盘推荐股票
     **/
    @Scheduled(cron = "0 40 14 * * *")
    public void fetchBigYinData() {
        RecmdStock recmdStockDel = new RecmdStock();
        BeiLiStrategy beiLiStrategy = new BeiLiStrategy();
        BigYinLineStrategy bigYinLineStrategy = new BigYinLineStrategy();
        bigYinLineStrategy.setNext(beiLiStrategy);
        stockDataService.processData(bigYinLineStrategy);
    }

}