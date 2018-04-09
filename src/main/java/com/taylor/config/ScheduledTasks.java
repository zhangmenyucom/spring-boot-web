package com.taylor.config;

import com.taylor.common.Constants;
import com.taylor.common.StockUtils;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockOnShelf;
import com.taylor.entity.stock.TencentTodayBaseInfo;
import com.taylor.service.RecmdStockService;
import com.taylor.service.StockDataService;
import com.taylor.service.StockOnShelfService;
import com.taylor.stock.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.taylor.common.CommonRequest.getStckTodayBaseInfo;

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
                TencentTodayBaseInfo stckTodayBaseInfo = getStckTodayBaseInfo(recmdStock.getStockCode().toLowerCase());
                if (stckTodayBaseInfo != null) {
                    stckTodayBaseInfo.setStockCode(recmdStock.getStockCode());
                    recmdStockService.updateUpDownRatio(stckTodayBaseInfo);
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
    @Scheduled(cron = "0 30 22 * * *")
    public void fetchRecmdData() {
        ShiZiMacdStrategy shiZiMacdStrategy = new ShiZiMacdStrategy();
        Over5DayStrategy over5DayStrategy = new Over5DayStrategy();
        BigYinLineStrategy bigYinLineStrategy = new BigYinLineStrategy();
        BeiLiStrategy beiLiStrategy = new BeiLiStrategy();
        FiveOverTenStrategy fiveOverTenStrategy = new FiveOverTenStrategy();
        OverYaLiStrategy overYaLiStrategy=new OverYaLiStrategy();
        shiZiMacdStrategy.setNext(over5DayStrategy);
        over5DayStrategy.setNext(bigYinLineStrategy);
        bigYinLineStrategy.setNext(beiLiStrategy);
        beiLiStrategy.setNext(overYaLiStrategy);
        overYaLiStrategy.setNext(fiveOverTenStrategy);

        List<Integer> strategyTypeList = new ArrayList<>();
        /**清除当天及5天以外的数据**/
        IStrategy iStrategy=shiZiMacdStrategy;
        do {
            strategyTypeList.add(iStrategy.getStrategyEnum().getCode());
            iStrategy = iStrategy.getNext();
        } while (iStrategy != null);
        recmdStockService.delByStrategyList(strategyTypeList);
        stockDataService.processData(shiZiMacdStrategy);
    }

    /**
     * 天鹅拳股票
     **/
    @Scheduled(cron = "0 0 22 * * *")
    public void fetchTianEData() {
        RecmdStock recmdStockDel = new RecmdStock();
        IStrategy iStrategy = new TianEQuanStrategy();
        List<Integer> strategyTypeList = new ArrayList<>();
        /**清除当天及5天以外的数据**/
        do {
            strategyTypeList.add(iStrategy.getStrategyEnum().getCode());
            iStrategy = iStrategy.getNext();
        } while (iStrategy != null);
        recmdStockService.delByStrategyList(strategyTypeList);
        stockDataService.processData(iStrategy, 10);
    }

    /**
     * 尾盘推荐股票
     **/
    @Scheduled(cron = "0 30 14 * * *")
    public void fetchBigYinData() {
        RecmdStock recmdStockDel = new RecmdStock();
        BeiLiStrategy beiLiStrategy = new BeiLiStrategy();
        Over5DayStrategy over5DayStrategy = new Over5DayStrategy();
        Over10DayStrategy over10DayStrategy = new Over10DayStrategy();
        Over20DayStrategy over20DayStrategy = new Over20DayStrategy();
        BigYinLineStrategy bigYinLineStrategy = new BigYinLineStrategy();
        OverYaLiStrategy overYaLiStrategy = new OverYaLiStrategy();
        FiveOverTenStrategy fiveOverTenStrategy = new FiveOverTenStrategy();
        bigYinLineStrategy.setNext(beiLiStrategy);
        beiLiStrategy.setNext(over5DayStrategy);
        over5DayStrategy.setNext(over10DayStrategy);
        over10DayStrategy.setNext(over20DayStrategy);
        over20DayStrategy.setNext(overYaLiStrategy);
        overYaLiStrategy.setNext(fiveOverTenStrategy);
        IStrategy iStrategy = bigYinLineStrategy;
        List<Integer> strategyTypeList = new ArrayList<>();
        /**清除当天及5天以外的数据**/
        do {
            strategyTypeList.add(iStrategy.getStrategyEnum().getCode());
            iStrategy = iStrategy.getNext();
        } while (iStrategy != null);
        recmdStockService.delByStrategyList(strategyTypeList);
        stockDataService.processData(bigYinLineStrategy);
    }

}