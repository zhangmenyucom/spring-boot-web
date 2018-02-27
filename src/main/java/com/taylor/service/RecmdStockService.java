package com.taylor.service;

import com.taylor.common.CrudService;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockBaseInfo;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 0:32
 */
public interface RecmdStockService extends CrudService<RecmdStock,RecmdStock> {


    void checkResult();

    int updateUpDownRatio(StockBaseInfo stockBaseInfo);

    List<RecmdStock> getRecmdStockByCountTime();

    int updateGuZhenScore(RecmdStock recmdStockUpdate);
}
