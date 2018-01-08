package com.taylor.stock.strategy;

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
    public MacdStrategy(String name) {
        super(name);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        Double diff = 0.02D;
        MashData today = mashDataList.get(0);
        MashData yestoday = mashDataList.get(1);
        /**
         * today>=0 and yestoday<0
         */
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
