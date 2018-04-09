package com.taylor.stock.strategy;

import com.taylor.common.CommonRequest;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.entity.stock.TencentDayData;
import com.taylor.stock.common.StrategyEnum;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class YiYangChuanSanXianStrategy extends IStrategy {

    public YiYangChuanSanXianStrategy() {
        super(StrategyEnum.TYPE24);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        MashData mashDataToday = mashDataList.get(0);
        StockPanKouData stockPanKouData = CommonRequest.getStockPanKouData(mashDataToday.getStockCode().toLowerCase());

        /**今日上涨**/
        if (mashDataToday.getKline().getNetChangeRatio() <= 0) {
            return 0;
        }
        /**换手率大于1**/
        if (stockPanKouData.getExchangeRatio() < 1) {
            return 0;
        }
        List<TencentDayData> stckDailyHistory = CommonRequest.getStckDailyHistory(mashDataList.get(0).getStockCode(), 2);

        /**放量**/
        if (stckDailyHistory.get(1).getTotalHands() - stckDailyHistory.get(0).getTotalHands() < 0) {
            return 0;
        }

        /**1阳穿3线**/
        MashData mashDataYestoday = mashDataList.get(1);
        if (mashDataToday.getMa5().getAvgPrice() > mashDataToday.getKline().getLow() && mashDataToday.getMa5().getAvgPrice() < mashDataToday.getKline().getHigh()) {
            if (mashDataToday.getMa10().getAvgPrice() > mashDataToday.getKline().getLow() && mashDataToday.getMa10().getAvgPrice() < mashDataToday.getKline().getHigh()) {
                if (mashDataToday.getMa20().getAvgPrice() > mashDataToday.getKline().getLow() && mashDataToday.getMa20().getAvgPrice() < mashDataToday.getKline().getHigh()) {
                    return 1;
                }
            }
        }
        return 0;
    }
}