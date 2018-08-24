package com.taylor.yicai.entity;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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

    public static Order getDanShuangOrder(int t, BillEnum billEnum) {
        return new Order(t, billEnum);

    }

    private Order(int t, BillEnum billEnum) {
        BetGameEnum randomBetGame = BetGameEnum.getRandomBetGame();
        BetStrategyEnum randomBetStrategy = BetStrategyEnum.getRandomBetStrategy();
        this.i = randomBetGame.getGameId();
        this.c = randomBetStrategy.getContent();
        this.n = randomBetStrategy.getN();
        this.t = t;
        this.m = billEnum.getUnit();
        this.k = 0;
        this.a = billEnum.getValue().multiply(BigDecimal.valueOf(2 * t * n));
    }

    public static Order getZuliuOrder(int times) {
        return new Order()
                .setC(BetZuLiuStategy.generateNextNumber())
                .setI(20979)
                .setN(56)
                .setT(times)
                .setM(4)
                .setK(0)
                .setA(BigDecimal.valueOf(0.112 * times));
    }
}
