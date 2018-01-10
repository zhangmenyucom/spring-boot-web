package com.taylor.stock.strategy;

import com.taylor.entity.stock.MashData;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class Kdj10Strategy extends IStrategy {

    public Kdj10Strategy(String name) {
        super(name);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        int kdDiff = 10;
        MashData today = mashDataList.get(0);
        MashData yestoday = mashDataList.get(1);
        /**
         * 今日：0<=k-d<=10,昨天0<=d-k<=10,macd<0
         */
        if (today.getKdj().getK() - today.getKdj().getD() >= 0 && today.getKdj().getK() - today.getKdj().getD() <= kdDiff && yestoday.getKdj().getD() - yestoday.getKdj().getK() >= 0 && yestoday.getKdj().getD() - yestoday.getKdj().getK() <= kdDiff && today.getMacd().getMacd() <= 0) {
            return 1;
        }
        /**
         * 今日：0<=d-k<=10,昨天0<=k-d<=10,macd>0
         */
        if (today.getKdj().getD() - today.getKdj().getK() >= 0 && today.getKdj().getD() - today.getKdj().getK() <= kdDiff && yestoday.getKdj().getK() - yestoday.getKdj().getD() >= 0 && yestoday.getKdj().getK() - yestoday.getKdj().getD() <= kdDiff && today.getMacd().getMacd() >= 0) {
            return -1;
        }
        return 0;
    }
}
