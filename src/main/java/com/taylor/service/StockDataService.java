package com.taylor.service;

import com.taylor.common.CrudService;
import com.taylor.entity.StockData;
import com.taylor.stock.strategy.IStrategy;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 0:32
 */
public interface StockDataService extends CrudService<StockData, StockData> {

    void processData(IStrategy strategy,Integer pan);

    List<StockData> findDataByCodeType(String  stockCode);

    List<String> getIndustryList();

    List<StockData> findDataByIndustryName(String name);
}
