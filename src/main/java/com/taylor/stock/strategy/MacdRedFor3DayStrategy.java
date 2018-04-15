package com.taylor.stock.strategy;

import com.taylor.common.CommonRequest;
import com.taylor.common.Constants;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.TencentDayData;
import com.taylor.stock.common.StrategyEnum;
import lombok.Data;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:21
 */
@Data
public class MacdRedFor3DayStrategy extends IStrategy {
    public MacdRedFor3DayStrategy() {
        super(StrategyEnum.TYPE26);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        MashData today = mashDataList.get(0);
        /**股价不能太高**/
        if (today.getKline().getClose() > Constants.CURRENT_PRICE_LIMIT) {
            return 0;
        }

        MashData yestoday = mashDataList.get(1);
        MashData yestodayBefore = mashDataList.get(2);
        MashData yestodayBeforeBefore = mashDataList.get(3);
        /**最近三天macd红，三天前金叉**/
        if (today.getMacd().getMacd() < 0 || yestoday.getMacd().getMacd() < 0 || yestodayBefore.getMacd().getMacd() < 0 || yestodayBeforeBefore.getMacd().getMacd() > 0) {
            return 0;
        }
        List<TencentDayData> stckDailyHistory = CommonRequest.getStckDailyHistory(mashDataList.get(0).getStockCode(), 5);
        if (stckDailyHistory.get(4).getTotalHands() > stckDailyHistory.get(3).getTotalHands() && stckDailyHistory.get(3).getTotalHands() > stckDailyHistory.get(2).getTotalHands()) {
            return 1;
        }
        return 0;
    }
}
