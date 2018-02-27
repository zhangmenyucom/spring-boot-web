package com.taylor.stock.strategy;

import com.taylor.common.CommonRequest;
import com.taylor.common.Constants;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.stock.common.StrategyEnum;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class KdjOverWithRatioStrategy extends IStrategy {

    private Float kdiff;
    private Float ratio;

    public KdjOverWithRatioStrategy(Float kdiff, Float ratio) {
        super(StrategyEnum.TYPE7);
        this.kdiff=kdiff;
        this.ratio=ratio;
    }
    @Override
    public int doCheck(List<MashData> mashDataList) {
        MashData today = mashDataList.get(0);
        if(today.getKline().getClose()> Constants.CURRENT_PRICE_LIMIT){
            return 0;
        }
        /**
         * 今日：k-d>kdiff,macd<0
         */
        if (today.getKdj().getK() - today.getKdj().getD() >= kdiff && today.getMacd().getMacd() <= 0) {
            StockPanKouData stockPanKouData = CommonRequest.getStockPanKouData(today.getStockCode().toLowerCase());
            if (stockPanKouData!=null) {
                if (stockPanKouData.getExchangeRatio() >= ratio) {
                    return 1;
                }
            }
            return 0;
        }
        return 0;
    }
}
