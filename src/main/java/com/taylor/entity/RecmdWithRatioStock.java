package com.taylor.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author xiaolu.zhang
 * @desc:包含换手率
 * @date: 2018/1/6 2:53
 */
@Data
public class RecmdWithRatioStock {
    /**id**/
    private Long id;

    /**股票代码**/
    private String stockCode;

    /**股票名称**/
    private String stockName;

    /**
     * 换手率
     **/
    private double turnoverRatio;

    /**操作意见**/
    private String recmdOperate;

    /**算法策略**/
    private String strategy;
}
