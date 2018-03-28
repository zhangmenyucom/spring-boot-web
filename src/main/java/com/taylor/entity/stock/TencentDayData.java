package com.taylor.entity.stock;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/3/28 16:35
 */
@Data
public class TencentDayData  implements Serializable{
    private static final long serialVersionUID = 6865720686988375775L;
    private String date;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private Long totalHands;
}
