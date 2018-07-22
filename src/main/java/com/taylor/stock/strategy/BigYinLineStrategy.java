package com.taylor.stock.strategy;

import com.taylor.entity.stock.HistoryData;
import com.taylor.stock.common.StrategyEnum;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class BigYinLineStrategy extends IStrategy {

    public BigYinLineStrategy() {
        super(StrategyEnum.TYPE20);
    }

    @Override
    public int doCheck(List<HistoryData> historyData, String stockCode) {
        /**至少有十个交易日数据吧**/
        if (historyData == null || historyData.size() < 10) {
            return 0;
        }
        HistoryData today = historyData.get(historyData.size() - 1);
        if ((today.getOpen() >= today.getClose()) && today.getVolume() / historyData.get(historyData.size() - 2).getVolume() >= 1.5) {
            for (int i = 0; i < historyData.size() - 1; i++) {
                if (today.getVolume() / historyData.get(i).getVolume() > 1.5) {
                    continue;
                }
                return 0;
            }
            return 1;
        }
        return 0;
    }
}
