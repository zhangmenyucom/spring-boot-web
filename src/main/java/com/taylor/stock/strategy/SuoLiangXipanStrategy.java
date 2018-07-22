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
        if (historyData.get(historyData.size() - 2).getVolume() / historyData.get(historyData.size() - 1).getVolume()> 1.5) {
            return 1;
        }
        return 0;
    }
}
