package com.taylor.stock.strategy;

import com.taylor.common.CommonRequest;
import com.taylor.entity.stock.TencentDayData;
import com.taylor.entity.stock.TencentTodayBaseInfo;
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
    public int doCheck(TencentTodayBaseInfo stckTodayBaseInfo) {

        List<TencentDayData> stckDailyHistory = CommonRequest.getStckDailyHistory(stckTodayBaseInfo.getStockCode(), 2);
        if (stckDailyHistory == null && stckDailyHistory.size() < 2) {
            return 0;
        }
        if (stckDailyHistory.get(stckDailyHistory.size() - 2).getTotalHands() / stckDailyHistory.get(stckDailyHistory.size() - 1).getTotalHands() > 1.5) {
            return 1;
        }
        return 0;
    }
}
