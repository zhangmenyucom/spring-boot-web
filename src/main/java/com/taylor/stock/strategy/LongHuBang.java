package com.taylor.stock.strategy;

import com.taylor.api.ApiClient;
import com.taylor.entity.stock.HistoryData;
import com.taylor.stock.common.StrategyEnum;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class LongHuBang extends IStrategy {
    public LongHuBang() {
        super(StrategyEnum.TYPE29);
    }

    @Override
    public int doCheck(List<HistoryData> historyData, String stockCode) {
        /**至少有十个交易日数据吧**/
        if (historyData == null || historyData.size() < 10) {
            return 0;
        }
        if (ApiClient.getPanKouData(stockCode).getUpDownMountPercent() > 9.0d) {
            return 1;
        }
        return 0;
    }
}
