package com.taylor.stock.strategy;

import com.taylor.entity.stock.HistoryData;
import com.taylor.stock.common.StrategyEnum;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class TwoDayStopStrategy extends IStrategy {

    public TwoDayStopStrategy() {
        super(StrategyEnum.TYPE32);
    }

    @Override
    public int doCheck(List<HistoryData> historyData, String stockCode) {
        /**至少有十个交易日数据吧**/
        if (historyData == null || historyData.size() < 10) {
            return 0;
        }
        for (int i = 0; i < historyData.size() - 2; i++) {
            if ((historyData.get(i + 1).getHigh() - historyData.get(i).getClose()) / historyData.get(i).getClose() > 0.09 && (historyData.get(i + 2).getHigh() - historyData.get(i + 1).getClose()) / historyData.get(i + 1).getClose() > 0.09) {
                return 1;
            }
        }
        return 0;
    }
}
