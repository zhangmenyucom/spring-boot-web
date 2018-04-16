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
public class JinJiaQinNiuStrategy extends IStrategy {

    public JinJiaQinNiuStrategy() {
        super(StrategyEnum.TYPE27);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        List<TencentDayData> stckDailyHistory = CommonRequest.getStckDailyHistory(mashDataList.get(0).getStockCode(), 1);
        StockPanKouData stockPanKouData = CommonRequest.getStockPanKouData(mashDataList.get(0).getStockCode().toLowerCase());
        TencentDayData tencentDayData = stckDailyHistory.get(0);
        if (tencentDayData.getTotalHands() < 6000) {
            return 0;
        }
        if (stockPanKouData.getUpDownMountPercent() > 3 && stockPanKouData.getUpDownMountPercent() < 4) {
            if (stockPanKouData.getLiangBi() > 3) {
                return 1;
            }
        }
        return 0;
    }
}
