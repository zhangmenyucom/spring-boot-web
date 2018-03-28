package com.taylor.stock.strategy;

import com.taylor.common.CommonRequest;
import com.taylor.common.KLineTypeEnum;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.entity.stock.kdj.MacdTimeBean;
import com.taylor.stock.common.StrategyEnum;
import com.taylor.stock.request.MacdTimeDataRequest;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class BeiLiStrategy extends IStrategy {

    public BeiLiStrategy() {
        super(StrategyEnum.TYPE21);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        String stockCode = mashDataList.get(0).getStockCode();
        /**停牌的去除**/
        StockPanKouData stockPanKouData = CommonRequest.getStockPanKouData(stockCode);
        if (stockPanKouData.getB1Number() <= 1) {
            return 0;
        }
        List<MacdTimeBean> macdTimeBean5 = MacdTimeDataRequest.getKdjTimeList(stockCode, KLineTypeEnum.FIVE_MINI, 10);
        if (Math.abs(macdTimeBean5.get(macdTimeBean5.size() - 1).getClose_px() - macdTimeBean5.get(macdTimeBean5.size() - 1).getLow_px()) / macdTimeBean5.get(0).getClose_px() <= 0.005) {
            return 0;
        }
        for (int i = 0; i <= macdTimeBean5.size() - 2; i++) {
            if (macdTimeBean5.get(i).getDiff() < macdTimeBean5.get(macdTimeBean5.size() - 1).getDiff()) {
            } else {
                return 0;
            }
        }

        List<MacdTimeBean> macdTimeBean15 = MacdTimeDataRequest.getKdjTimeList(stockCode, KLineTypeEnum.FIVETEEN_MINI, 6);
        if (Math.abs(macdTimeBean15.get(macdTimeBean15.size() - 1).getClose_px() - macdTimeBean15.get(macdTimeBean15.size() - 1).getLow_px()) / macdTimeBean15.get(0).getClose_px() <= 0.005) {
            return 0;
        }
        for (int i = macdTimeBean15.size() - 2; i >= 0; i--) {
            if (macdTimeBean15.get(i).getDiff() < macdTimeBean15.get(macdTimeBean15.size() - 1).getDiff()) {
            } else {
                return 0;
            }
        }

        List<MacdTimeBean> macdTimeBean30 = MacdTimeDataRequest.getKdjTimeList(stockCode, KLineTypeEnum.THIRTY_MINI, 3);
        if (Math.abs(macdTimeBean30.get(macdTimeBean30.size() - 1).getClose_px() - macdTimeBean30.get(macdTimeBean30.size() - 1).getLow_px()) / macdTimeBean30.get(0).getClose_px() <= 0.005) {
            return 0;
        }
        for (int i = macdTimeBean30.size() - 2; i >= 0; i--) {
            if (macdTimeBean30.get(i).getDiff() < macdTimeBean30.get(macdTimeBean30.size() - 1).getDiff()) {
            } else {
                return 0;
            }
        }
        return 1;
    }
}
