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
        /**近十个交易日内有涨停**/
        for (int i = historyData.size() - 9; i < historyData.size() - 1; i++) {
            //涨幅大于9%
            if ((historyData.get(i).getClose() - historyData.get(i - 1).getClose()) / historyData.get(i - 1).getClose() > 0.05f) {
                //前期不能大于这个涨停价
                for (int k = i - 1; k >= 0; k--) {
                    if (historyData.get(k).getHigh() > historyData.get(i).getClose()) {
                        return 0;
                    }
                }
                //后期必须小于前者
                for (int j = i + 1; j <= historyData.size() - 1; j++) {
                    if (historyData.get(j).getHigh() > historyData.get(i).getClose()) {
                        return 0;
                    }
                }
                if (historyData.get(historyData.size() - 1).getClose() < historyData.get(i).getLow()) {
                    return 0;
                }
                return 1;
            }
        }
        return 0;
    }
}
