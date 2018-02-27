package com.taylor.stock.strategy;

import com.taylor.common.CommonRequest;
import com.taylor.common.Constants;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.stock.common.StrategyEnum;
import com.taylor.stock.request.QueryStockWeekDataRequest;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class KdjWeekRatioStrategyLiangBiOver1 extends IStrategy {

    private Float ratio;
    private static HttpMethodBase methodWeek = new GetMethod(Constants.METHOD_URL_STOCK_WEEK_INFO);

    public KdjWeekRatioStrategyLiangBiOver1(Float ratio) {
        super(StrategyEnum.TYPE10);
        this.ratio = ratio;
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        MashData today = mashDataList.get(0);
        if (today.getKline().getClose() > Constants.CURRENT_PRICE_LIMIT) {
            return 0;
        }
        MashData yestoday = mashDataList.get(1);
        if (today.getKdj().getK() - today.getKdj().getD() >= 0 && yestoday.getKdj().getD() - yestoday.getKdj().getK() >= 0) {
            StockPanKouData stockPanKouData = CommonRequest.getStockPanKouData(today.getStockCode().toLowerCase());
            if (stockPanKouData != null && stockPanKouData.getLiangBi() > 1.1 && stockPanKouData.getOuter() > stockPanKouData.getInner()) {
                if (stockPanKouData.getExchangeRatio() >= ratio) {
                    List<MashData> mashDataList2 = QueryStockWeekDataRequest.queryLatestResult(today.getStockCode().toLowerCase(), methodWeek);
                    if (mashDataList2 == null || mashDataList2.size() < 2) {
                        return 0;
                    } else {
                        MashData mashDataLastOneWeek = mashDataList2.get(0);
                        MashData mashDataLastTwoWeek = mashDataList2.get(1);
                        if (mashDataLastOneWeek.getKdj().getD() - mashDataLastTwoWeek.getKdj().getD() > 0 && mashDataLastOneWeek.getKdj().getK() - mashDataLastTwoWeek.getKdj().getK() > 0) {
                            return 1;
                        }
                    }
                }
            }
        }
        return 0;
    }
}
