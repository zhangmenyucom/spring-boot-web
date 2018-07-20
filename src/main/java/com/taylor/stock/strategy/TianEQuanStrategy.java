package com.taylor.stock.strategy;

import com.taylor.common.CommonRequest;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.TencentTodayBaseInfo;
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
    public int doCheck(TencentTodayBaseInfo tencentTodayBaseInfo) {
        List<MashData> mashDataList = CommonRequest.queryLatestResult(tencentTodayBaseInfo.getStockCode(), 10);
        /**至少有十个交易日数据吧**/
        if (mashDataList.size() < 10) {
            return 0;
        }

        MashData today = mashDataList.get(0);

        /**十字结构,涨幅-1至1以内**/
        if (Math.abs(today.getKline().getNetChangeRatio()) <= 5) {
            /**近十个交易日内有涨停**/
            for (int i = 0; i < 10; i++) {
                if (checkTopStop(mashDataList.get(i)) && today.getKline().getLow() < mashDataList.get(i).getKline().getClose()) {
                    return 1;
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
