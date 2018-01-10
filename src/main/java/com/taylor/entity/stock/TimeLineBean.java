package com.taylor.entity.stock;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc: 分时图数据
 * @date: 2018/1/10 12:12
 */
@Data
public class TimeLineBean {
    /**
     * 日期
     **/
    private String date;

    /**
     * 时间
     **/
    private String time;

    /**
     * 时时价
     **/
    private double price;

    /**
     * 成交量-手
     **/
    private Long volume;

    /**
     * 均价
     **/
    private double avgPrice;

    private int ccl;

    /**
     * 涨跌幅
     **/
    private double netChangeRatio;

    /**
     * 昨日收盘价
     **/
    private double preClose;


    private Long amount;
}
