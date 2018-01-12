package com.taylor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/12 11:44
 */
@Data
public class StockOnShelf implements Serializable{

    private static final long serialVersionUID = 3043197316971800509L;
    private Long id;

    private String stockCode;

    private String stockName;

    private Integer amount;

    private Double buyPrice;

    private Double currentPrice;

    private Double soldPrice;

    private Double netRation;

    private String recmdOperate;

    private Integer status;
}
