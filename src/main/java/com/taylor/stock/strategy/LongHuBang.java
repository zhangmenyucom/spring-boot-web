package com.taylor.stock.strategy;

import com.taylor.common.CommonRequest;
import com.taylor.common.KLineTypeEnum;
import com.taylor.entity.TongHuaShunStockBase;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.kdj.MacdTimeBean;
import com.taylor.stock.common.StrategyEnum;
import com.taylor.stock.request.MacdTimeDataRequest;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class LongHuBang extends IStrategy {
    public LongHuBang() {
        super(StrategyEnum.TYPE29);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        String stockCode = mashDataList.get(0).getStockCode();
        if (CommonRequest.getStckTodayBaseInfo(stockCode).getUpDownPercent() > 9.0d) {
            return 1;
        }
        return 0;
    }
}
