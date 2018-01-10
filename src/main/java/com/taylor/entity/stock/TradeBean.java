package com.taylor.entity.stock;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:买卖方数据
 * @date: 2018/1/10 12:04
 */
@Data
public class TradeBean {
    private double price;
    private int volume;
}
