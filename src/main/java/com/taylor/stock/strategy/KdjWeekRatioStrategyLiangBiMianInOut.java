package com.taylor.stock.strategy;

import com.taylor.common.CommonRequest;
import com.taylor.common.Constants;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.StockFundInOut;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.stock.request.QueryStockWeekDataRequest;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class KdjWeekRatioStrategyLiangBiMianInOut extends IStrategy {

    private Float ratio;
    private static HttpMethodBase methodWeek = new GetMethod(Constants.METHOD_URL_STOCK_WEEK_INFO);

    public KdjWeekRatioStrategyLiangBiMianInOut(Float ratio) {
        super("日kdj金叉，周kdj上翘，量比大于1.1，外盘大于内盘，主力净流入正值,且流入比大于10%，ratio大于" + ratio);
        this.ratio = ratio;
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        if(mashDataList==null||mashDataList.isEmpty()){
            return 0;
        }
        if(mashDataList.size()<=1){
            return 0;
        }
        MashData mashData3=null;
        if(mashDataList.size()>=3){
            mashData3=mashDataList.get(2);
        }
        MashData today = mashDataList.get(0);
        if (today.getKline().getClose() > Constants.CURRENT_PRICE_LIMIT) {
            return 0;
        }
        MashData yestoday = mashDataList.get(1);
        if (today.getKdj().getK() - today.getKdj().getD() >= 0 && (yestoday.getKdj().getD() - yestoday.getKdj().getK() >= 0 || (mashData3 != null && mashData3.getKdj().getD() - mashData3.getKdj().getK() >= 0))) {
            /**今日盘口数据***/
            StockPanKouData stockPanKouData = CommonRequest.getStockPanKouData(today.getBlockCode().toLowerCase());
            /**今日主力注入情况**/
            StockFundInOut stockFundInOut=CommonRequest.getStockFundInOutData(today.getBlockCode().toLowerCase());
            if (stockPanKouData != null && stockPanKouData.getLiangBi() > 1.1 && stockPanKouData.getOuter() > stockPanKouData.getInner() && stockFundInOut.getMainTotalIn()>0 && stockFundInOut.getMainInBi()>10.0) {
                if (stockPanKouData.getExchangeRatio() >= ratio) {
                    /**周kdj值上翘**/
                    List<MashData> mashDataList2 = QueryStockWeekDataRequest.queryLatestResult(today.getBlockCode().toLowerCase(), methodWeek);
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
