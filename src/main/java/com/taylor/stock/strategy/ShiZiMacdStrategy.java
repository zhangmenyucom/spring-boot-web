package com.taylor.stock.strategy;

import com.taylor.common.Constants;
import com.taylor.entity.stock.MashData;
import com.taylor.stock.common.StrategyEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:21
 */
@Data
@EqualsAndHashCode(callSuper = false)

public class ShiZiMacdStrategy extends IStrategy {
    public ShiZiMacdStrategy() {
        super(StrategyEnum.TYPE13);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        MashData today = mashDataList.get(0);
        if (today.getKline().getClose() > Constants.CURRENT_PRICE_LIMIT) {
            return 0;
        }
        MashData yestoday = mashDataList.get(1);

        if (Math.abs(today.getKline().getNetChangeRatio()) <= 1.0 && today.getKline().getHigh() - today.getKline().getOpen() >= 0.1 && today.getKline().getOpen() - today.getKline().getLow() >= 0.1) {
            Double diff = 0.02D;
            if (today.getMacd().getMacd() <= 0 && today.getKdj().getK() - today.getKdj().getD() >= 0.02) {
                return 1;
            }
        }
        return 0;
    }
}
