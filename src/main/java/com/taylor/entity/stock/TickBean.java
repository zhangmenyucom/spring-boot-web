package com.taylor.entity.stock;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:交易明细
 * @date: 2018/1/10 12:07
 */
@Data
public class TickBean {

    /**交易日期**/
    private String date;

    /**交易时间**/
    private String time;

    /**成交价**/
    private double price;

    /**成交量**/
    private long volume;

    /**B=主动性买入S=主动性卖出M=中性盘**/
    private String bsflag;

    private int ccl;
}
