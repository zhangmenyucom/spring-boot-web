package com.taylor.stock.strategy;

import com.taylor.entity.stock.MashData;
import com.taylor.stock.common.StrategyEnum;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class FiveOverTenStrategy extends IStrategy {

    public FiveOverTenStrategy() {
        super(StrategyEnum.TYPE23);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        MashData mashDataToday = mashDataList.get(0);
        MashData mashDataYestoday = mashDataList.get(1);
        if ((mashDataToday.getMa5().getAvgPrice() - mashDataToday.getMa10().getAvgPrice() > 0) && (mashDataYestoday.getMa5().getAvgPrice() - mashDataYestoday.getMa10().getAvgPrice() < 0)) {
            return 1;
        }
        return 0;
    }
}
