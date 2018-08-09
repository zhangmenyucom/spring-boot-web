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

    public Order(int i, BetStrategyEnum betStrategyEnum, int t, BillEnum billEnum) {
        this.i = i;
        this.c = betStrategyEnum.getContent();
        this.n = betStrategyEnum.getN();
        this.t = t;
        this.m = billEnum.getUnit();
        this.k = 0;
        this.a = billEnum.getValue().multiply(BigDecimal.valueOf(2 * t * n));
    }

    public boolean just(String result) {
        String[] split = result.split(",");
        switch (c) {
            case "单|双":
                if (Integer.valueOf(split[2]) % 2 != 0 && Integer.valueOf(split[3]) % 2 == 0) {
                    return true;
                }
                return false;
            case "双|单":
                if (Integer.valueOf(split[2]) % 2 == 0 && Integer.valueOf(split[3]) % 2 != 0) {
                    return true;
                }
                return false;
            case "单,双|单":
                if (Integer.valueOf(split[3]) % 2 != 0) {
                    return true;
                }
                return false;
            case "单,双|双":
                if (Integer.valueOf(split[3]) % 2 == 0) {
                    return true;
                }
                return false;
            case "单|单,双":
                if (Integer.valueOf(split[2]) % 2 != 0) {
                    return true;
                }
                return false;
            case "双|单,双":
                if (Integer.valueOf(split[2]) % 2 == 0) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        Order order = new Order(21023, BetStrategyEnum.DS_S, 1, BillEnum.LI);

        System.out.println(order.just("1,2,5,2"));
    }

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
}
