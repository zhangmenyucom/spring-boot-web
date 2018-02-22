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
public class KdjDayWeekMonthXStrategy extends IStrategy {

    private Float ratio;

    private static HttpMethodBase methodWeek = new GetMethod(Constants.METHOD_URL_STOCK_WEEK_INFO);

    private static HttpMethodBase methodMonth = new GetMethod(Constants.METHOD_URL_STOCK_MONTH_INFO);

    public KdjDayWeekMonthXStrategy() {
        super(StrategyEnum.TYPE5);
    }


    @Override
    public int doCheck(List<MashData> mashDataList) {
        MashData today = mashDataList.get(0);
        if (today.getKline().getClose() > Constants.CURRENT_PRICE_LIMIT) {
            return 0;
        }
        MashData yestoday = mashDataList.get(1);
        if (today.getKdj().getK()-yestoday.getKdj().getK() >= 0 && today.getKdj().getK()-today.getKdj().getD()>0) {
            StockPanKouData stockPanKouData = CommonRequest.getStockPanKouData(today.getBlockCode().toLowerCase());
            if (stockPanKouData!=null) {
                    List<MashData> mashDataList2 = QueryStockWeekDataRequest.queryLatestResult(today.getBlockCode().toLowerCase(), methodWeek);
                    if (mashDataList2==null || mashDataList2.size() < 2) {
                        return 0;
                    } else {
                        MashData mashDataLastOneWeek = mashDataList2.get(0);
                        MashData mashDataLastTwoWeek = mashDataList2.get(1);
                        if (mashDataLastOneWeek.getKdj().getK()-mashDataLastTwoWeek.getKdj().getK()>0 && mashDataLastOneWeek.getKdj().getK()-mashDataLastOneWeek.getKdj().getD()>0) {
                            List<MashData> mashDataList3 = QueryStockWeekDataRequest.queryLatestResult(today.getBlockCode().toLowerCase(), methodMonth);
                            if (mashDataList3==null || mashDataList3.size() < 2) {
                                return 0;
                            }else{
                                MashData mashDataLastOneMonth = mashDataList3.get(0);
                                MashData mashDataLastTwoMoth = mashDataList3.get(1);
                                if (mashDataLastOneMonth.getKdj().getK()-mashDataLastTwoMoth.getKdj().getK()>0 && mashDataLastOneMonth.getKdj().getK()-mashDataLastOneMonth.getKdj().getD()>0) {
                                    return 1;
                                }
                            }
                        }
                    }
                }
            }
        return 0;
    }
}
