package com.taylor.service.impl;

import com.taylor.common.AbstractCrudService;
import com.taylor.common.ProcessCountor;
import com.taylor.dao.StockDataDao;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockData;
import com.taylor.service.RecmdStockService;
import com.taylor.service.StockDataService;
import com.taylor.stock.request.QueryStockDayDataRequest;
import com.taylor.stock.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.taylor.common.ConstantsInits.STOCK_CODE_LIST_SH;
import static com.taylor.common.ConstantsInits.STOCK_CODE_LIST_SZ;

/**
 * @author Administrator
 */
@Service
public class StockDataServiceImpl extends AbstractCrudService<StockData, StockData, StockDataDao> implements StockDataService {

    @Autowired
    private RecmdStockService recmdStockService;

    @Override
    public void processData(IStrategy strategy) {
        RecmdStock recmdStock = new RecmdStock();
        recmdStock.setStrategy(strategy.getName());
        /**清空数据**/
        recmdStockService.del(recmdStock);
        new QueryStockDayDataRequest(strategy, recmdStockService, STOCK_CODE_LIST_SH.subList(0, STOCK_CODE_LIST_SH.size() / 4), "stock_sh_4-1").start();
        new QueryStockDayDataRequest(strategy, recmdStockService, STOCK_CODE_LIST_SH.subList(STOCK_CODE_LIST_SH.size() / 4 + 1, STOCK_CODE_LIST_SH.size() / 2), "stock_sh_4-2").start();
        new QueryStockDayDataRequest(strategy, recmdStockService, STOCK_CODE_LIST_SH.subList(STOCK_CODE_LIST_SH.size() / 2 + 1, STOCK_CODE_LIST_SH.size() * 3 / 4), "stock_sh_4-3").start();
        new QueryStockDayDataRequest(strategy, recmdStockService, STOCK_CODE_LIST_SH.subList(STOCK_CODE_LIST_SH.size() * 3 / 4 + 1, STOCK_CODE_LIST_SH.size()), "stock_sh_4-4").start();

        new QueryStockDayDataRequest(strategy, recmdStockService, STOCK_CODE_LIST_SZ.subList(0, STOCK_CODE_LIST_SZ.size() / 4), "stock_sz_4-1").start();
        new QueryStockDayDataRequest(strategy, recmdStockService, STOCK_CODE_LIST_SZ.subList(STOCK_CODE_LIST_SZ.size() / 4 + 1, STOCK_CODE_LIST_SZ.size() / 2), "stock_sz_4-2").start();
        new QueryStockDayDataRequest(strategy, recmdStockService, STOCK_CODE_LIST_SZ.subList(STOCK_CODE_LIST_SZ.size() / 2 + 1, STOCK_CODE_LIST_SZ.size() * 3 / 4), "stock_sz_4-3").start();
        new QueryStockDayDataRequest(strategy, recmdStockService, STOCK_CODE_LIST_SZ.subList(STOCK_CODE_LIST_SZ.size() * 3 / 4 + 1, STOCK_CODE_LIST_SZ.size()), "stock_sz_4-4").start();
        new ProcessCountor().start();
    }
}
