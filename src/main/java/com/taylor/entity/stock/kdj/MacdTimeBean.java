package com.taylor.entity.stock.kdj;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/30 20:11
 */
@Data
public class MacdTimeBean {
    /**
     * 最小时间
     **/
    private Long min_time;
    /**
     * 开盘价
     **/
    private Double open_px;
    /**
     * 最高价
     **/
    private Double high_px;
    /**
     * 最低价
     **/
    private Double low_px;
    /**
     * 收盘价
     **/
    private Double close_px;

    /**
     * 交易量（手）
     **/
    private Double business_amount;

    /**
     * 交易额
     **/
    private Double business_balance;

    /**
     * kdj_k数据
     **/
    private Double diff;

    /**
     * kdj_d数据
     **/
    private Double dea;

    /**
     * kdj_数据
     **/
    private Double macd;

    /**
     * 价格变化量（5分钟内）
     **/
    private Double c_px_change;

    /**
     * 价格变化比（5分钟内
     **/
    private Double c_px_change_percent;
}
