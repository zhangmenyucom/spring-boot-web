package com.taylor.stock.strategy;

import com.taylor.entity.stock.MashData;
import com.taylor.stock.common.StrategyEnum;
import lombok.Data;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:12
 */
@Data
public abstract class IStrategy {
    protected StrategyEnum strategyEnum;

    protected IStrategy next;

    IStrategy(StrategyEnum strategyEnum) {
        this.strategyEnum = strategyEnum;
    }

    public abstract int doCheck(List<MashData> mashDataList);

    public boolean hasNext() {
        return this.next != null;
    }

}
