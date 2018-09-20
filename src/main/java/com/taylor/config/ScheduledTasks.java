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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        if (!StockUtils.noNeedMonotorTime()) {
            List<RecmdStock> recmdStocks = recmdStockService.find(new RecmdStock());
            log.info("正在刷新推荐股票数据...........");
            for (RecmdStock rcmd : recmdStocks) {
                StockPanKouData panKouData = ApiClient.getPanKouData(rcmd.getStockCode().toLowerCase());
                /**两分钟内如果涨幅超过2%,添加到异动股票数据**/
                if (!StockUtils.noNeedMonotorForYiDongTime()) {
                    if ((panKouData.getCurrentPrice() - rcmd.getCurrentPrice()) / panKouData.getOpenPrice() >= 0.02d) {
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
    }

    /**
     * 每30秒刷新股架数据
     */
    @Scheduled(cron = "*/30 * * * * *")
    public void updateShelfData() {
        if (!StockUtils.noNeedMonotorTime()) {
            log.info("正在刷新股架数据...........");
            stockOnShelfService.updateSelf(new StockOnShelf().setStatus(0));
        }
    }

    /**
     * 每1分钟刷新持仓数据
     */
    @Scheduled(cron = "0 * * * * *")
    public void updateStoreData() {
        if (!StockUtils.noNeedMonotorTime()) {
            DecimalFormat df = new DecimalFormat("0.00");
            List<StockOnShelf> stockOnShelves = stockOnShelfService.find(new StockOnShelf().setStatus(1));
            log.info("正在刷新推荐股票数据...........");
            for (StockOnShelf onShelf : stockOnShelves) {
                StockPanKouData panKouData = ApiClient.getPanKouData(onShelf.getStockCode().toLowerCase());
                /**一分钟内如果涨幅超过1%**/
                if (!StockUtils.noNeedMonotorForYiDongTime()) {
                    double discount = (panKouData.getCurrentPrice() - onShelf.getCurrentPrice()) / panKouData.getOpenPrice();
                    if (Math.abs(discount) >= 0.01d) {
                        MailUtils.send139Mail(panKouData.getStockName() + (discount > 0.0d ? "拉升" : "抛盘") + df.format(discount * 100) + "%现涨" + df.format(panKouData.getUpDownMountPercent()) + "%", "");
                    }
                }
                if (panKouData != null) {
                    onShelf.setCurrentPrice(panKouData.getCurrentPrice());
                    stockOnShelfService.update(onShelf);
                }
            }
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