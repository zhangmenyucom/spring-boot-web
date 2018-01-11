package com.taylor.stock.strategy;

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
public class KdjOverWithRatioStrategy extends IStrategy {

    private HttpMethodBase method;
    private Float kdiff;
    private Float ratio;

    public KdjOverWithRatioStrategy(String name, Float kdiff, Float ratio, HttpMethodBase method) {
        super(name);
        this.method = method;
        this.kdiff=kdiff;
        this.ratio=ratio;
    }
    @Override
    public int doCheck(List<MashData> mashDataList) {
        MashData today = mashDataList.get(0);
        /**
         * 今日：k-d>kdiff,macd<0
         */
        if (today.getKdj().getK() - today.getKdj().getD() >= kdiff && today.getMacd().getMacd() <= 0) {
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
