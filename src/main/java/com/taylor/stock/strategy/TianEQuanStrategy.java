package com.taylor.stock.strategy;

import com.taylor.common.CommonRequest;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.stock.common.StrategyEnum;
import lombok.Data;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
@Data
public class TianEQuanStrategy extends IStrategy {


    public TianEQuanStrategy() {
        super(StrategyEnum.TYPE16);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        if (mashDataList.size() <= 10) {
            return 0;
        }

        MashData today = mashDataList.get(0);

        /**十字结构**/
        if (Math.abs(today.getKline().getNetChangeRatio()) <= 1.0 && today.getKline().getHigh()-today.getKline().getOpen()>=0.1 && today.getKline().getOpen()-today.getKline().getLow()>=0.1) {
            /**突破10日均线**/
            if(today.getKline().getClose()-today.getMa10().getAvgPrice()>=-0.1){
                /**近十个交易日内有涨停**/
                for(int i = 0; i < 10; i++) {
                    if (checkTopStop(mashDataList.get(i))) {
                        if(i>=2) {
                            return 1;
                        }
                        break;
                    }
                }
            }
        }
        return 0;
    }

    private boolean checkTopStop(MashData mashData) {
        if (mashData.getKline().getNetChangeRatio() >= 9.9f) {
            return true;
        }
        return false;
    }
}
