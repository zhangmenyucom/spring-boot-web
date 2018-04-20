package com.taylor.stock.strategy;

import com.taylor.common.CommonRequest;
import com.taylor.entity.TongHuaShunStockBase;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.entity.stock.TencentDayData;
import com.taylor.stock.common.StrategyEnum;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class JinJiaQinNiuStrategy extends IStrategy {

    public JinJiaQinNiuStrategy() {
        super(StrategyEnum.TYPE27);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        TongHuaShunStockBase baseDataFromTongHuaShun = CommonRequest.getBaseDataFromTongHuaShun(mashDataList.get(0).getStockCode());
        if (baseDataFromTongHuaShun.getToltalHands() < 6000) {
            return 0;
        }
        if (baseDataFromTongHuaShun.getNetChangeRatio() > 3 && baseDataFromTongHuaShun.getNetChangeRatio() < 4) {
            if (baseDataFromTongHuaShun.getLiangBi() > 3) {
                return 1;
            }
        }
        return 0;
    }
}
