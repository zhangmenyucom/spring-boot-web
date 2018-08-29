package com.taylor.service.impl;

import com.taylor.common.AbstractCrudService;
import com.taylor.dao.RecmdStockDao;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.entity.stock.TencentTodayBaseInfo;
import com.taylor.service.RecmdStockService;
import com.taylor.stock.request.RecmdUpdator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class RecmdStockServiceImpl extends AbstractCrudService<RecmdStock, RecmdStock, RecmdStockDao> implements RecmdStockService {


    @Override
    public void checkResult() {
        new RecmdUpdator(new RecmdStock(), this).start();

    }

    @Override
    public int updateUpDownRatio(StockPanKouData stockBaseInfo) {
        return this.getDao().updateRecmdTodayUpDownRatio(stockBaseInfo);
    }

    @Override
    public List<RecmdStock> getRecmdStockByCountTime(Date recordTime) {
        return this.getDao().getRecmdStockByCountTime(recordTime);
    }

    @Override
    public int updateGuZhenScore(RecmdStock recmdStockUpdate) {
        return this.getDao().updateGuZhenScore(recmdStockUpdate);
    }

    @Override
    public int delByStrategyList(List<Integer> strategyTypeList) {
        return this.getDao().delByStrategyList(strategyTypeList);
    }
}
