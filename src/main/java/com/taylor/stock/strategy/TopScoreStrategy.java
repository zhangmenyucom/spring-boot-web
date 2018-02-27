package com.taylor.stock.strategy;

import com.taylor.entity.stock.GuZhenResponse;
import com.taylor.entity.stock.MashData;
import com.taylor.stock.common.StrategyEnum;
import com.taylor.stock.request.GuZhenRequest;
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
public class TopScoreStrategy extends IStrategy {
    public TopScoreStrategy() {
        super(StrategyEnum.TYPE17);
    }

    @Override
    public synchronized int doCheck(List<MashData> mashDataList) {
        GuZhenResponse guZhengData = GuZhenRequest.getGuZhengData(mashDataList.get(0).getStockCode());
        if (guZhengData != null && guZhengData.getData().getData().getResult().get_score() >= 6.0 && guZhengData.getData().getData().getResult().get_ko() > 80) {
            return 1;
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
