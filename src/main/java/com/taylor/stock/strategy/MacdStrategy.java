package com.taylor.stock.strategy;

import com.taylor.common.Constants;
import com.taylor.entity.stock.MashData;
import lombok.Data;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:21
 */
@Data
public class MacdStrategy extends IStrategy {
    public MacdStrategy() {
        super("macd在-0.02至+0.02之间");
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        MashData today = mashDataList.get(0);
        if (today.getKline().getClose() > Constants.CURRENT_PRICE_LIMIT) {
            return 0;
        }
        MashData yestoday = mashDataList.get(1);
        /**
         * today>=0 and yestoday<0
         */
        Double diff = 0.02D;
        if (today.getMacd().getMacd() >= 0 && yestoday.getMacd().getMacd() <= 0 && Math.abs(today.getMacd().getMacd()) <= diff) {
            return 1;
        }
        /**
         * today>=0 and yestoday<0
         */
        if (today.getMacd().getMacd() <= 0 && yestoday.getMacd().getMacd() >= 0 && Math.abs(today.getMacd().getMacd()) <= diff) {
            return -1;
        }
        return 0;
    }
}
