package com.taylor.entity.stock;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/3/28 16:35
 */
@Data
public class TencentTodayBaseInfo implements Serializable{
    private static final long serialVersionUID = 6865720686988375735L;
    private String stockName;
    private Double open;
    private Double close;
    private Double preClose;
    private Double high;
    private Double upDownValue;
    private Double upDownPercent;
    private Double low;
    private Double liangbi;
    private Long totalHands;
    private Double exchangeRatio;
}
