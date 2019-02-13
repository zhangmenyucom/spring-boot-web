package com.taylor.stock.strategy;

import com.taylor.common.Constants;
import com.taylor.entity.stock.HistoryData;
import com.taylor.stock.common.StrategyEnum;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class VXinDiStrategy extends IStrategy {

    public VXinDiStrategy() {
        super(StrategyEnum.TYPE35);
    }

    @Override
    public int doCheck(List<HistoryData> historyData, String stockCode) {
        /**至少有十个交易日数据吧**/
        if (historyData == null || historyData.size() < 10) {
            return 0;
        }
        HistoryData today = historyData.get(historyData.size() - 1);
        if (today.getClose() > Constants.CURRENT_PRICE_LIMIT) {
            return 0;
        }
        HistoryData yestoday = historyData.get(historyData.size() - 2);
        HistoryData theDayBeforeYes = historyData.get(historyData.size() - 3);

        /**当天十字星**/
        if (Math.abs((today.getClose() - today.getOpen()) / today.getOpen()) <= 0.015 && Math.abs((today.getHigh() - today.getLow()) / today.getClose()) > 0.025) {
            /**昨天大阳或大阴**/
            if (Math.abs((yestoday.getClose() - yestoday.getOpen()) / yestoday.getOpen()) >= 0.025) {
                /**前天十字星**/
                if (Math.abs((theDayBeforeYes.getClose() - theDayBeforeYes.getOpen()) / theDayBeforeYes.getOpen()) <= 0.015 && Math.abs((theDayBeforeYes.getHigh() - theDayBeforeYes.getLow()) / theDayBeforeYes.getClose()) > 0.025) {
                    /**当天与前天收盘价无差别**/
                    if (Math.abs((today.getClose() - theDayBeforeYes.getClose()) / theDayBeforeYes.getClose()) <= 0.015) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
}
