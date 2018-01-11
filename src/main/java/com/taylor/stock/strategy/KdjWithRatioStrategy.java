package com.taylor.stock.strategy;

import com.taylor.common.Constants;
import com.taylor.entity.StockBaseInfo;
import com.taylor.entity.stock.MashData;
import com.taylor.stock.request.QueryStockBaseDataRequest;
import org.apache.commons.httpclient.HttpMethodBase;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class KdjWithRatioStrategy extends IStrategy {

    private HttpMethodBase method;
    private Float kdiff;
    private Float ratio;

    public KdjWithRatioStrategy(Float kdiff, Float ratio, HttpMethodBase method) {
        super("kdj差值小于"+kdiff+"换手率大于"+ratio);
        this.method = method;
        this.kdiff=kdiff;
        this.ratio=ratio;
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        MashData today = mashDataList.get(0);
        if(today.getKline().getClose()> Constants.CURRENT_PRICE_LIMIT){
            return 0;
        }
        MashData yestoday = mashDataList.get(1);
        /**
         * 今日：0<=k-d<=5,昨天0<=d-k<=5,macd<0
         */
        if (today.getKdj().getK() - today.getKdj().getD() >=0 && today.getKdj().getK() - today.getKdj().getD() <= kdiff && yestoday.getKdj().getD() - yestoday.getKdj().getK() >= 0 && yestoday.getKdj().getD() - yestoday.getKdj().getK() <= kdiff && today.getMacd().getMacd() <= 0) {
            List<StockBaseInfo> stockBaseInfos = QueryStockBaseDataRequest.queryStockBaseInfo(today.getBlockCode(), method);
            if (!stockBaseInfos.isEmpty()) {
                if (stockBaseInfos.get(0).getTurnoverRatio() >= ratio) {
                    return 1;
                }
            }
            return 0;
        }
        return 0;
    }
}
