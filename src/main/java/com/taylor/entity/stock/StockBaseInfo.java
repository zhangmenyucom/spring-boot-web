package com.taylor.entity.stock;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 19:22
 */
@Data
public class StockBaseInfo implements Serializable {

    private static final long serialVersionUID = 4413621543793129829L;

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
}
