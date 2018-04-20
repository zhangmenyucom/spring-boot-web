package com.taylor.stock.strategy;

import com.taylor.entity.stock.MashData;
import com.taylor.stock.common.StrategyEnum;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class FiveOverTenOverTwentyStrategy extends IStrategy {

    public FiveOverTenOverTwentyStrategy() {
        super(StrategyEnum.TYPE28);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        MashData today = mashDataList.get(0);
        if (today.getMa5().getAvgPrice() > today.getMa10().getAvgPrice() && today.getMa10().getAvgPrice() > today.getMa20().getAvgPrice()) {
            return 1;
        } else {
            return 0;
        }
    }
}
