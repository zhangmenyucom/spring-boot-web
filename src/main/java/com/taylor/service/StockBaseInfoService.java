package com.taylor.service;

import com.taylor.entity.StockBaseInfo;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 19:38
 */
public interface StockBaseInfoService {

    StockBaseInfo getStockBaseInfo(String stockCode);
}
