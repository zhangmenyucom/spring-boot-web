package com.taylor.service.impl;

import com.taylor.common.AbstractCrudService;
import com.taylor.dao.StockDataDao;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockData;
import com.taylor.service.RecmdStockService;
import com.taylor.service.StockDataService;
import com.taylor.stock.request.QueryStockDayDataRequest;
import com.taylor.stock.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.taylor.common.ConstantsInits.STOCK_CODE_LIST_CHUANGYE;
import static com.taylor.common.ConstantsInits.STOCK_CODE_LIST_MAIN;
import static com.taylor.common.ConstantsInits.STOCK_CODE_LIST_ZHONGXIAO;

/**
 * @author Administrator
 */
@Service
public class StockDataServiceImpl extends AbstractCrudService<StockData, StockData, StockDataDao> implements StockDataService {

    @Autowired
    private RecmdStockService recmdStockService;

    @Autowired
    private StockDataService stockDataService;

    private static final int DEFALUT_COUNT = 5;

    private static final int THREAD_HOLD = 4;


    @Override
    public void processData(IStrategy strategy, Integer pan) {
        QueryStockDayDataRequest.run_flag = 1;
        RecmdStock recmdStock = new RecmdStock();
        recmdStock.setStrategyType(strategy.getStrategyEnum().getCode());
        /**清空数据**/
        recmdStockService.del(recmdStock);
        List<StockData> stockDataList = stockDataService.find(new StockData().setType(pan + 1));
        for (int i = 0; i < THREAD_HOLD; i++) {
            new QueryStockDayDataRequest(strategy, recmdStockService, stockDataList.subList(i * stockDataList.size() / THREAD_HOLD, (i + 1) * stockDataList.size() / THREAD_HOLD - 1), "stock_sh_" + THREAD_HOLD + "-" + i).start();
        }
    }

    @Override
    public List<StockData> findDataByCodeType(String stockCode) {
        return this.getDao().findDataByCodeType(stockCode);
    }
}
