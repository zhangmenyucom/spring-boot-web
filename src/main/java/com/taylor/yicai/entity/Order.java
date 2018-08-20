package com.taylor.yicai.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/8/9 1:45
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Order {

    /**
     * 投注id
     **/
    private int i;
    /**
     * 投注内容
     **/
    private String c;

    private int n;
    /**
     * 投注倍数
     **/
    private int t;
    /**
     * 返现率
     **/
    private int k;
    /**
     * 金额单位 1元 2角 3分 4里
     **/
    private int m;
    /**
     * 投注金额
     **/
    private BigDecimal a;

    public Order(BetGameEnum betGameEnum, BetStrategyEnum betStrategyEnum, int t, BillEnum billEnum) {
        this.i = betGameEnum.getGameId();
        this.c = betStrategyEnum.getContent();
        this.n = betStrategyEnum.getN();
        this.t = t;
        this.m = billEnum.getUnit();
        this.k = 0;
        this.a = billEnum.getValue().multiply(BigDecimal.valueOf(2 * t * n));
    }
}
