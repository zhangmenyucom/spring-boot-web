package com.taylor.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 0:26
 */
@Data
@Accessors(chain = true)
public class StockData {
    private Long id;
    private String stockCode;
    private String stockName;
    private String industry;
}
