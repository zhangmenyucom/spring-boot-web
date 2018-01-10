package com.taylor.service;

import com.taylor.entity.stock.StockBaseInfo;
import com.taylor.entity.stock.query.StockBaseQueryBean;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 19:38
 */
public interface StockBaseInfoService {

    List<StockBaseInfo> getStockBaseInfo(StockBaseQueryBean stockBaseQueryBean);
}
