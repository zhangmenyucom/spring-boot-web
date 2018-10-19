package com.taylor.stock.strategy;

import com.taylor.entity.stock.HistoryData;
import com.taylor.stock.common.StrategyEnum;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class SuoLiangXipanStrategy extends IStrategy {

    public SuoLiangXipanStrategy() {
        super(StrategyEnum.TYPE30);
    }

    @Override
    public int doCheck(List<HistoryData> historyData, String stockCode) {
        /**至少有十个交易日数据吧**/
        if (historyData == null || historyData.size() < 10) {
            return 0;
        }
        HistoryData yestoday = historyData.get(historyData.size() - 2);
        HistoryData today = historyData.get(historyData.size() - 1);
        HistoryData theDaybefore = historyData.get(historyData.size() - 3);
        if ((yestoday.getClose() - theDaybefore.getClose() > 0) && (yestoday.getHigh() - yestoday.getClose()) / theDaybefore.getClose() <= 0.02 && yestoday.getVolume() / today.getVolume() > 1.2) {
            return 1;
        }
        return 0;
    }
}