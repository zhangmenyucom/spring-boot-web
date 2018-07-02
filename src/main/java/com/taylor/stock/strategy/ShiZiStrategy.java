package com.taylor.stock.strategy;

import com.taylor.common.Constants;
import com.taylor.entity.stock.MashData;
import com.taylor.stock.common.StrategyEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class ShiZiStrategy extends IStrategy {
    public ShiZiStrategy() {
        super(StrategyEnum.TYPE13);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        if (mashDataList == null || mashDataList.size() < 2) {
            return 0;
        }
        MashData today = mashDataList.get(0);
        if (today.getKline().getClose() > Constants.CURRENT_PRICE_LIMIT) {
            return 0;
        }
        MashData yestoday = mashDataList.get(1);
        if (yestoday.getKline().getNetChangeRatio() < -2.0 && Math.abs(today.getKline().getNetChangeRatio()) <= 1 && Math.abs(today.getKline().getClose() - today.getKline().getLow()) / today.getKline().getPreClose() >0.02) {
            return 1;
        }
        return 0;
    }
}
