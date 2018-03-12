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
public class Over20DayStrategy extends IStrategy {

    public Over20DayStrategy() {
        super(StrategyEnum.TYPE17);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        MashData today = mashDataList.get(0);
        MashData yestoday = mashDataList.get(1);
        if (yestoday.getKline().getHigh() < yestoday.getMa20().getAvgPrice() && today.getKline().getLow() > today.getMa20().getAvgPrice()) {
            return 1;
        } else {
            return 0;
        }

    }
}
