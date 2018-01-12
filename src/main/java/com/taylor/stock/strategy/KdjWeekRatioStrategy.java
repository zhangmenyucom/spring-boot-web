package com.taylor.stock.strategy;

import com.taylor.common.Constants;
import com.taylor.entity.StockBaseInfo;
import com.taylor.entity.stock.MashData;
import com.taylor.stock.request.QueryStockBaseDataRequest;
import com.taylor.stock.request.QueryStockWeekDataRequest;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class KdjWeekRatioStrategy extends IStrategy {

    private HttpMethodBase method;
    private Float ratio;
    private GetMethod methodWeek = new GetMethod(Constants.METHOD_URL_STOCK_WEEK_INFO);

    public KdjWeekRatioStrategy(Float ratio, HttpMethodBase method) {
        super("日kdj金叉，周kdj上翘，macd不限ratio大于" + ratio);
        this.method = method;
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
            List<StockBaseInfo> stockBaseInfos = QueryStockBaseDataRequest.queryStockBaseInfo(today.getBlockCode(), method);
            if (!stockBaseInfos.isEmpty()) {
                if (stockBaseInfos.get(0).getTurnoverRatio() >= ratio) {
                    List<MashData> mashDataList2 = QueryStockWeekDataRequest.queryLatestResult(today.getBlockCode().toLowerCase(), methodWeek);
                    if (mashDataList2.size() < 2) {
                        return 1;
                    } else {
                        MashData mashDataLastOneWeek = mashDataList2.get(0);
                        MashData mashDataLastTwoWeek = mashDataList2.get(1);
                        if (mashDataLastOneWeek.getKdj().getD()-mashDataLastTwoWeek.getKdj().getD()>0 &&mashDataLastOneWeek.getKdj().getK()-mashDataLastTwoWeek.getKdj().getK()>0) {
                            return 1;
                        }
                    }
                }
            }
        }
        return 0;
    }
}
