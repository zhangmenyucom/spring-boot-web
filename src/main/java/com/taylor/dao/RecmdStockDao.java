package com.taylor.dao;

import com.taylor.common.BaseDao;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockBaseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 0:27
 */
public interface RecmdStockDao extends BaseDao<RecmdStock,RecmdStock> {
    /**
     * @param stockBaseInfo
     * @desc
     */
    int updateRecmdTodayUpDownRatio(@Param("stockBaseInfo") StockBaseInfo stockBaseInfo);

    List<RecmdStock> getRecmdStockByCountTime();
}
