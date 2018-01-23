package com.taylor.stock.strategy;

import com.taylor.common.CommonRequest;
import com.taylor.common.Constants;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.StockPanKouData;
import org.apache.commons.httpclient.HttpMethodBase;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class KdjOver45duRatioStrategy extends IStrategy {

    private Float kdiff;
    private Float ratio;

    public KdjOver45duRatioStrategy(Float kdiff, Float ratio) {
        super("今天kdj差/昨天kdj差>1,今天kdiff大于"+kdiff+",ratio大于"+ratio);
        this.kdiff = kdiff;
        this.ratio = ratio;
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        MashData today = mashDataList.get(0);
        if(today.getKline().getClose()> Constants.CURRENT_PRICE_LIMIT){
            return 0;
        }
        MashData yestoday = mashDataList.get(1);

        if (today.getKdj().getK() - today.getKdj().getD() >= kdiff  && today.getMacd().getMacd() <= 0) {
            if (((today.getKdj().getK() - today.getKdj().getD()) / Math.abs(yestoday.getKdj().getD() - yestoday.getKdj().getK())) >= 1) {
                StockPanKouData stockPanKouData = CommonRequest.getStockPanKouData(today.getBlockCode().toLowerCase());
                if (stockPanKouData!=null) {
                    if (stockPanKouData.getExchangeRatio() >= ratio) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
}
