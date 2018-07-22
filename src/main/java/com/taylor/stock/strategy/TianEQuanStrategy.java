package com.taylor.stock.strategy;

import com.taylor.entity.stock.HistoryData;
import com.taylor.stock.common.StrategyEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TianEQuanStrategy extends IStrategy {


    public TianEQuanStrategy() {
        super(StrategyEnum.TYPE16);
    }

    @Override
    public int doCheck(List<HistoryData> historyData, String stockCode) {
        /**至少有十个交易日数据吧**/
        if (historyData == null || historyData.size() < 10) {
            return 0;
        }

        HistoryData today = historyData.get(historyData.size()-1);

        /**近十个交易日内有涨停**/
        for (int i = 1; i < 10; i++) {
            if ((historyData.get(i).getClose() - historyData.get(i - 1).getClose()) / historyData.get(i - 1).getClose() > 0.09f && today.getLow() < today.getClose() && historyData.get(i).getClose() > today.getClose()) {
                return 1;
            }
        }
        return 0;
    }
}
