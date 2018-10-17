package com.taylor.stock.strategy;

import com.taylor.common.Constants;
import com.taylor.entity.stock.HistoryData;
import com.taylor.stock.common.StrategyEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class ShiZiStrategy extends IStrategy {
    public ShiZiStrategy() {
        super(StrategyEnum.TYPE13);
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

        if (Math.abs((today.getClose() - today.getOpen()) / yestoday.getClose()) <= 0.01 && Math.abs(today.getClose() - today.getLow()) / yestoday.getClose() > 0.02) {
            if (Math.abs((yestoday.getClose() - yestoday.getOpen()) / theDayBeforeYes.getClose()) <= 0.01 && Math.abs(yestoday.getClose() - yestoday.getLow()) / theDayBeforeYes.getClose() > 0.02) {
                return 1;
            }
        }
        return 0;
    }
}
