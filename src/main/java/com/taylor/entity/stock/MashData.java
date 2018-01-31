package com.taylor.entity.stock;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/5 16:52
 */
@Data
public class MashData {

    private String blockCode;

    private String date;

    private KlineBean kline;

    private MaBean ma5;

    private MaBean ma10;

    private MaBean ma20;

    private MacdBean macd;

    private KdjBean kdj;

    private RSIBean rsi;

    private int kdjCount;
}
