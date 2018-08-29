package com.taylor.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 19:22
 */
@Data
@Accessors(chain = true)
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
    private BigDecimal close;

    /**
     * 最高价
     **/
    private BigDecimal high;

    /**
     * 最低价
     **/
    private BigDecimal low;

    /**
     * 流通市值
     **/
    private Long capitalization;

    /**
     * 单位值变化
     **/
    private BigDecimal netChange;

    /**
     * 涨幅
     **/
    private BigDecimal netChangeRatio;

    /**
     * 成交量
     **/
    private Long volume;

    /**
     * 振幅
     **/
    private BigDecimal amplitudeRatio;

    /**
     * 换手率
     **/
    private BigDecimal turnoverRatio;

    /**
     * 昨天收盘价
     **/
    private BigDecimal preClose;

    /**
     * 开盘价
     **/
    private BigDecimal open;


    private Integer asset;

    private String followNum;

}
