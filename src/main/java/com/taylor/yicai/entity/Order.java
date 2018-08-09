package com.taylor.yicai.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/8/9 1:45
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Order {

    public Order(String i, BetStrategyEnum betStrategyEnum, int t, BillEnum billEnum) {
        this.i = i;
        this.c = betStrategyEnum.getContent();
        this.n = betStrategyEnum.getN();
        this.t = t;
        this.m = billEnum.getUnit();
        this.k = 0;
        this.a = 2 * billEnum.getValue() * t * n;
    }

    /**
     * 投注id
     **/
    private String i;
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
    private float k;
    /**
     * 金额单位 1元 2角 3分 4里
     **/
    private int m;
    /**
     * 投注金额
     **/
    private float a;
}
