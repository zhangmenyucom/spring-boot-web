package com.taylor.service;

import com.taylor.common.CrudService;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.entity.stock.TencentTodayBaseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 0:32
 */
public interface RecmdStockService extends CrudService<RecmdStock,RecmdStock> {


    void checkResult();

    int updateUpDownRatio(StockPanKouData stockTodayBaseInfo);

    List<RecmdStock> getRecmdStockByCountTime(Date recordTime);

    int updateGuZhenScore(RecmdStock recmdStockUpdate);

    int delByStrategyList(@Param("strategyTypeList") List<Integer> strategyTypeList);
}
