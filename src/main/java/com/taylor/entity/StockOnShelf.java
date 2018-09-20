package com.taylor.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/12 11:44
 */
@Data
@Accessors(chain = true)
public class StockOnShelf implements Serializable{

    private static final long serialVersionUID = 3043197316971800509L;

    private Long id;

    /**股票代码**/
    private String stockCode;

    /**股票名称**/
    private String stockName;

    /**买入数量**/
    private Integer amount;

    /**成本价**/
    private Double buyPrice;

    /**关注时价**/
    private Double focusPrice;

    /**当前价**/
    private Double currentPrice;

    /**售价**/
    private Double soldPrice;

    /**涨跌**/
    private Double netRatio;

    /**5分钟涨跌**/
    private Double fiveMiniRatio;

    /**操作意见**/
    private String recmdOperate;

    /**状态**/
    private Integer status;

    /**入选理由**/
    private String reason;

    /**获取行业类型**/
    private String industry;
}
