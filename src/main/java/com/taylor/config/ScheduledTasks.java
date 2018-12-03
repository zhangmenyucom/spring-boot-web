package com.taylor.config;

import com.taylor.api.ApiClient;
import com.taylor.common.MailUtils;
import com.taylor.common.StockUtils;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockOnShelf;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.service.RecmdStockService;
import com.taylor.service.StockDataService;
import com.taylor.service.StockOnShelfService;
import com.taylor.stock.common.OperatorEnum;
import com.taylor.stock.common.StrategyEnum;
import com.taylor.stock.strategy.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author taylor
 */
@Component
@Slf4j
@Data
public class ScheduledTasks {

    @Autowired
    private StockDataService stockDataService;

    @Autowired
    private RecmdStockService recmdStockService;

    @Autowired
    private StockOnShelfService stockOnShelfService;

    /**
     * 每2分钟刷新推荐数据,并监测异动股票
     */
    @Scheduled(cron = "0 */2 * * * *")
    public void updateRecmdData() {
        Set<String> stockCodeSets = new HashSet<>();
        if (!StockUtils.noNeedMonotorTime()) {
            List<RecmdStock> recmdStocks = recmdStockService.find(new RecmdStock());
            log.info("正在刷新推荐股票数据...........");
            for (RecmdStock rcmd : recmdStocks) {
                if (stockCodeSets.contains(rcmd.getStockCode())) {
                    continue;
                }
                stockCodeSets.add(rcmd.getStockCode());
                StockPanKouData panKouData = ApiClient.getPanKouData(rcmd.getStockCode().toLowerCase());

                if (!StockUtils.noNeedMonotorForYiDongTime()) {
                    /**两分钟内如果涨幅超过3%,添加到异动股票数据**/
                    if (((panKouData.getCurrentPrice() - rcmd.getCurrentPrice()) / panKouData.getOpenPrice() >= 0.03d) && !Objects.equals(rcmd.getCurrentPrice(), panKouData.getYesPrice())) {
                        RecmdStock recmdStock = new RecmdStock().setRecordTime(new Date()).setStrategyType(StrategyEnum.TYPE28.getCode());
                        val stockIdList = recmdStockService.find(recmdStock).stream().map(RecmdStock::getStockCode).collect(Collectors.toList());
                        if (!stockIdList.contains(rcmd.getStockCode())) {
                            RecmdStock recmdStockNew = new RecmdStock()
                                    .setStockCode(rcmd.getStockCode())
                                    .setTurnoverRatio(panKouData.getExchangeRatio())
                                    .setStockName(panKouData.getStockName())
                                    .setRecordPrice(panKouData.getCurrentPrice())
                                    .setCurrentPrice(panKouData.getCurrentPrice())
                                    .setStrategy(StrategyEnum.TYPE28.getDesc())
                                    .setStrategyType(StrategyEnum.TYPE28.getCode())
                                    .setRecmdOperate(OperatorEnum.OPERATOR_ENUM_MAP.get(1))
                                    .setLiangbi(panKouData.getLiangBi())
                                    .setCostPrice(rcmd.getCurrentPrice())
                                    .setChangeRatioYestoday(panKouData.getUpDownMountPercent())
                                    .setOuterPan(panKouData.getOuter())
                                    .setInnerPan(panKouData.getInner());
                            recmdStockService.save(recmdStockNew);
                        }
                    }
                    if (panKouData != null) {
                        panKouData.setStockCode(rcmd.getStockCode());
                        recmdStockService.updateUpDownRatio(panKouData);
                    }
                }
            }
        }
        stockCodeSets.clear();
    }

    /**
     * 每30秒刷新股架数据
     */
    @Scheduled(cron = "*/30 * * * * *")
    public void updateShelfData() {
        if (!StockUtils.noNeedMonotorTime()) {
            log.info("正在刷新股架数据...........");
            stockOnShelfService.updateSelf(new StockOnShelf());
        }
    }

    /**
     * 尾盘推荐股票
     **/
    @Scheduled(cron = "0 0 22 * * *")
    public void fetchBigYinData() {
        RecmdStock recmdStockDel = new RecmdStock();
        /**放量大阴**/
        BigYinLineStrategy bigYinLineStrategy = new BigYinLineStrategy();
        /**龙虎榜**/
        LongHuBang longHuBang = new LongHuBang();
        bigYinLineStrategy.setNext(longHuBang);
        /**缩量洗盘**/
        SuoLiangXipanStrategy suoLiangXipanStrategy = new SuoLiangXipanStrategy();
        longHuBang.setNext(suoLiangXipanStrategy);
        /**底部十字或T型结构**/
        ShiZiStrategy shiZiStrategy = new ShiZiStrategy();
        suoLiangXipanStrategy.setNext(shiZiStrategy);
        /**天鹅拳形态**/
        TianEQuanStrategy tianEQuan = new TianEQuanStrategy();
        shiZiStrategy.setNext(tianEQuan);
        /**3与5之间**/
        Between3and5 between3and5 = new Between3and5();
        tianEQuan.setNext(between3and5);
        /**连续两天涨停**/
        TwoDayStopStrategy twoDayStopStrategy = new TwoDayStopStrategy();
        between3and5.setNext(twoDayStopStrategy);


        IStrategy iStrategy = bigYinLineStrategy;
        List<Integer> strategyTypeList = new ArrayList<>();
        /**清除当天及5天以外的数据**/
        do {
            strategyTypeList.add(iStrategy.getStrategyEnum().getCode());
            iStrategy = iStrategy.getNext();
        } while (iStrategy != null);
        recmdStockService.delByStrategyList(strategyTypeList);
        stockDataService.processData(bigYinLineStrategy, -1);
    }

    /**
     * 清除5日外异动股票
     **/
    @Scheduled(cron = "0 0 08 * * *")
    public void clearOldData() {
        List<Integer> strategyTypeList = new ArrayList<>();
        strategyTypeList.add(StrategyEnum.TYPE28.getCode());
        recmdStockService.delByStrategyList(strategyTypeList);
    }
}