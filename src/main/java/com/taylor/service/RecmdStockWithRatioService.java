package com.taylor.service;

import com.taylor.common.CrudService;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.RecmdWithRatioStock;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 0:32
 */
public interface RecmdStockWithRatioService extends CrudService<RecmdWithRatioStock,RecmdWithRatioStock> {


    void process(List<RecmdStock> recmdStocks);
}
