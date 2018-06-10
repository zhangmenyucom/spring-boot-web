package com.taylor.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 2:53
 */
@Data
public class RecmdStock {

    /**
     * id
     **/
    private Long id;

    /**
     * 股票代码
     **/
    private String stockCode;

    /**
     * 股票名称
     **/
    private String stockName;

    /**
     * 股票名称
     **/
    private String authorOpinion;

    /**
     * 记录时价格
     **/
    private Double recordPrice;

    /**
     * 当前价
     **/
    private Double currentPrice;

    /**
     * macd数据
     **/
    private Double macd;

    /**
     * kdj数据
     **/
    private String kdj;

    /*** 换手率**/
    private Double turnoverRatio;

    /**
     * 量比
     **/
    private Double liangbi;
    /**
     * 今日量比
     **/
    private Double liangbiToday;

    /**所属行业**/
    private String industry;

    /**
     * 营业收入比
     **/
    private Double majoGrow;

    /**
     * 净利润
     **/
    private Double netIncreaseRate;

    /**
     * 外盘
     **/
    private Double outerPan;

    /**
     * 内盘
     **/
    private Double innerPan;

    /**
     * 主力净流入（万）
     **/
    private Double mainIn;

    /**
     * 主力净入比
     **/
    private Double mainInBi;

    /*** 昨日涨幅**/
    private Double changeRatioYestoday;

    /*** 今日涨幅**/
    private Double changeRatioToday;

    /**
     * 最近kdj黄金交叉个数
     **/
    private int kdjCount;

    /**
     * 操作意见
     **/
    private String recmdOperate;

    /**
     * 算法策略类型
     **/
    private Integer strategyType;

    /**
     * 算法策略
     **/
    private String strategy;

    /**
     * 股诊分数
     **/
    private Float score;

    /**
     * 记录时间
     **/
    private Date recordTime;

    /**
     * 更新时间
     **/
    private Date updateTime;

}
