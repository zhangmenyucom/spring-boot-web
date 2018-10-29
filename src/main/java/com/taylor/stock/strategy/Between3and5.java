package com.taylor.stock.strategy;

import com.taylor.api.ApiClient;
import com.taylor.common.Constants;
import com.taylor.entity.stock.HistoryData;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.stock.common.StrategyEnum;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class Between3and5 extends IStrategy {
    public Between3and5() {
        super(StrategyEnum.TYPE31);
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
        StockPanKouData panKouData = ApiClient.getPanKouData(stockCode);
        if (panKouData.getUpDownMountPercent() >= 3.0d && panKouData.getUpDownMountPercent() <= 5.0d) {
            return 1;
            /*if (theDayBeforeYes.getClose() > yestoday.getClose() && theDayBeforeYes.getClose() > today.getClose() && today.getClose() > yestoday.getClose()) {
                return 1;
            }*/
        }
        return 0;
    }
}
