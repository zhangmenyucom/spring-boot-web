package com.taylor.entity.stock;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/5 16:54
 */
@Data
public class KlineBean {
    /**
     * 开盘价
     **/
    private double open;
    /**
     * 最高价
     **/
    private double high;
    /**
     * 最低价
     **/
    private double low;
    /**
     * 收盘价
     **/
    private double close;
    /**
     * 成交量
     **/
    private long volume;
    /**
     * 成交额
     **/
    private long amount;

    private String ccl;
    /**
     * 昨天收盘价
     **/
    private double preClose;

    /**
     * 今日涨幅
     **/
    private double netChangeRatio;
}
