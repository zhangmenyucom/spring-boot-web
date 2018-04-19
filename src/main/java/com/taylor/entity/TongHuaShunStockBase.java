package com.taylor.entity;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @date: 2018/4/19 18:28
 */
@Data
public class TongHuaShunStockBase {

    /**
     * 股票代码
     **/
    private String stockCode;

    /**
     * 股票名称
     **/
    private String stockName;

    /**
     * 交易所代号 sz,sh
     **/
    private String exchange;

    /**
     * 股票状态
     **/
    private String stockStatus;

    /***收盘价**/
    private double close;

    /**
     * 最高价
     **/
    private double high;

    /**
     * 最低价
     **/
    private double low;

    /**
     * 流通市值
     **/
    private Long capitalization;

    /**
     * 单位值变化
     **/
    private double netChange;

    /**
     * 涨幅
     **/
    private double netChangeRatio;

    /**
     * 成交量
     **/
    private Long volume;

    /**
     * 振幅
     **/
    private double amplitudeRatio;

    /**
     * 换手率
     **/
    private double turnoverRatio;

    /**
     * 昨天收盘价
     **/
    private double preClose;

    /**
     * 开盘价
     **/
    private double open;

    /**
     * 量比
     **/
    private Double liangBi;

    /**
     * 总手
     **/
    private Long toltalHands;

}
