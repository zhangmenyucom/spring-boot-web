package com.taylor.entity.stock;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/7/22 9:39
 */
@Data
public class HistoryData {
    private String day;
    private double open;
    private double high;
    private double low;
    private double close;
    private Long volume;
}
