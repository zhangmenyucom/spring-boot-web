package com.taylor.stock.strategy;

import com.taylor.entity.stock.MashData;
import com.taylor.stock.common.StrategyEnum;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:短期突破压力位
 * @date: 2018/1/9 0:18
 */
public class OverYaLiStrategy extends IStrategy {

    public OverYaLiStrategy() {
        super(StrategyEnum.TYPE22);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        int mid = mashDataList.size() / 2;
        int top = mashDataList.size() - 1;
        double yaliPrice = 0;
        for (int i = top; i >= mid; i--) {
            if (mashDataList.get(i).getKline().getHigh() > yaliPrice) {
                yaliPrice = mashDataList.get(i).getKline().getHigh();
            }
        }
        if (mashDataList.get(0).getKline().getLow() > yaliPrice && mashDataList.get(1).getKline().getClose() <= yaliPrice) {
            double temp = 0;
            int start = 0;
            for (int j = mid; j > start; j--) {
                temp = mashDataList.get(j).getKline().getHigh();
                if (temp > mashDataList.get(0).getKline().getLow()) {
                    return 0;
                }
            }
            if (temp < mashDataList.get(0).getKline().getLow()) {
                return 1;
            }
        }
        return 0;
    }
}
