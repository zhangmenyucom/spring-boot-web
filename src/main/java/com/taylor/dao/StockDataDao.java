package com.taylor.dao;

import com.taylor.common.BaseDao;
import com.taylor.entity.StockData;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 0:27
 */
public interface StockDataDao extends BaseDao<StockData,StockData> {

    List<StockData> findDataByCodeType(@RequestParam String stockCode);
}
