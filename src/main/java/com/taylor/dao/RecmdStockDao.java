package com.taylor.dao;

import com.taylor.common.BaseDao;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.stock.StockPanKouData;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 0:27
 */
public interface RecmdStockDao extends BaseDao<RecmdStock, RecmdStock> {
    /**
     * @param stockBaseInfo
     * @desc
     */
    int updateRecmdTodayUpDownRatio(@Param("stockBaseInfo") StockPanKouData stockBaseInfo);

    /**
     * @return
     */
    List<RecmdStock> getRecmdStockByCountTime(@Param("recordTime") Date recordTime);

    int updateGuZhenScore(@Param("entity") RecmdStock recmdStockUpdate);

    int delByStrategyList(@Param("strategyTypeList") List<Integer> strategyTypeList);
}
