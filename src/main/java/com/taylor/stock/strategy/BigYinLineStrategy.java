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
public class BigYinLineStrategy extends IStrategy {

    public BigYinLineStrategy() {
        super(StrategyEnum.TYPE20);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        List<TencentDayData> stckDailyHistory = CommonRequest.getStckDailyHistory(mashDataList.get(0).getStockCode(), 5);
        if (stckDailyHistory == null && stckDailyHistory.size() < 2) {
            return 0;
        }
        if (stckDailyHistory.get(stckDailyHistory.size() - 1).getTotalHands() / stckDailyHistory.get(stckDailyHistory.size() - 2).getTotalHands() >= 2) {
            for (int i = 0; i < stckDailyHistory.size() - 1; i++) {
                if (stckDailyHistory.get(stckDailyHistory.size() - 1).getTotalHands() / stckDailyHistory.get(i).getTotalHands() > 3) {
                    continue;
                }
                return 0;
            }
            return 1;
        }
        return 0;
    }
}
