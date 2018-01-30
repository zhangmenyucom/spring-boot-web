package com.taylor.entity.stock;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/30 14:26
 */
@Data
public class Kdj5QueryBean {
    private String path="/stock/stock/k_line";
    private KdjSubQueryBean data;
}
