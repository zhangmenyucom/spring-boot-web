package com.taylor.stock.strategy;

import com.taylor.common.Constants;
import com.taylor.entity.stock.MashData;
import com.taylor.stock.common.StrategyEnum;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class Kdj510Strategy extends IStrategy {

    public Kdj510Strategy() {
        super(StrategyEnum.TYPE4);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        int high = 10;
        int low=5;
        MashData today = mashDataList.get(0);
        if(today.getKline().getClose()> Constants.CURRENT_PRICE_LIMIT){
            return 0;
        }
        MashData yestoday = mashDataList.get(1);
        /**
         * 今日：5<=k-d<=10,昨天5<=d-k<=10,macd<0
         */
        if (today.getKdj().getK() - today.getKdj().getD() >= low && today.getKdj().getK() - today.getKdj().getD() <= high && yestoday.getKdj().getD() - yestoday.getKdj().getK() >= low && yestoday.getKdj().getD() - yestoday.getKdj().getK() <= high && today.getMacd().getMacd() <= 0) {
            return 1;
        }
        /**
         * 今日：5<=d-k<=10,昨天5<=k-d<=10,macd>0
         */
        if (today.getKdj().getD() - today.getKdj().getK() >= low && today.getKdj().getD() - today.getKdj().getK() <= high && yestoday.getKdj().getK() - yestoday.getKdj().getD() >= low && yestoday.getKdj().getK() - yestoday.getKdj().getD() <= high && today.getMacd().getMacd() >= 0) {
            return -1;
        }
        return 0;
    }
}
