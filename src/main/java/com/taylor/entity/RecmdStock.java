package com.taylor.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 2:53
 */
@Data
public class RecmdStock {

    /**id**/
    private Long id;

    /**股票代码**/
    private String stockCode;

    /**股票名称**/
    private String stockName;

    /**当前价**/
    private Double currentPrice;

    /**macd数据**/
    private Double macd;

    /**kdj数据**/
    private String kdj;

    /*** 换手率**/
    private double turnoverRatio;

    /**操作意见**/
    private String recmdOperate;

    /**算法策略**/
    private String strategy;

    /**创建时间**/
    private Date createTime;
}
