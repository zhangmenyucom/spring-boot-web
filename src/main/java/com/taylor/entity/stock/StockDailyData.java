package com.taylor.entity.stock;

import lombok.Data;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/5 17:13
 */
@Data
public class StockDailyData {
    /**
     * 日期
     **/
    private String date;

    /**
     * 数据列表
     **/
    private List<MashData> mashData;
}
