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
    @Scheduled(cron = "0 */5 * * * *")
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
     * 尾盘推荐股票
     **/
    @Scheduled(cron = "0 50 16 * * *")
    public void fetchBigYinData() {
        RecmdStock recmdStockDel = new RecmdStock();
        /**放量大阴**/
        BigYinLineStrategy bigYinLineStrategy=new BigYinLineStrategy();
        /**龙虎榜**/
        LongHuBang longHuBang=new LongHuBang();
        bigYinLineStrategy.setNext(longHuBang);
        /**突破短期压力**/
        OverYaLiStrategy overYaLiStrategy=new OverYaLiStrategy();
        longHuBang.setNext(overYaLiStrategy);
        /**缩量洗盘**/
        SuoLiangXipanStrategy suoLiangXipanStrategy=new SuoLiangXipanStrategy();
        overYaLiStrategy.setNext(suoLiangXipanStrategy);
        /**底部十字或T型结构**/
        ShiZiStrategy shiZiStrategy=new ShiZiStrategy();
        suoLiangXipanStrategy.setNext(shiZiStrategy);
        /**天鹅拳形态**/
        TianEQuanStrategy tianEQuan=new TianEQuanStrategy();
        shiZiStrategy.setNext(tianEQuan);
        IStrategy iStrategy = bigYinLineStrategy;
        List<Integer> strategyTypeList = new ArrayList<>();
        /**清除当天及5天以外的数据**/
        do {
            strategyTypeList.add(iStrategy.getStrategyEnum().getCode());
            iStrategy = iStrategy.getNext();
        } while (iStrategy != null);
        recmdStockService.delByStrategyList(strategyTypeList);
        stockDataService.processData(bigYinLineStrategy,10);
    }

}