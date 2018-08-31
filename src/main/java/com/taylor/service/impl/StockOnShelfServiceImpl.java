package com.taylor.service.impl;

import com.taylor.api.ApiClient;
import com.taylor.common.AbstractCrudService;
import com.taylor.dao.StockOnShelfDao;
import com.taylor.entity.StockOnShelf;
import com.taylor.entity.stock.TimeLineBean;
import com.taylor.entity.stock.TimeStockDataResponse;
import com.taylor.service.StockOnShelfService;
import com.taylor.stock.request.OnShelfUpdator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class StockOnShelfServiceImpl extends AbstractCrudService<StockOnShelf, StockOnShelf, StockOnShelfDao> implements StockOnShelfService {

    @Override
    public void updateSelf(StockOnShelf stockOnShelfQuery) {
        new OnShelfUpdator(stockOnShelfQuery, this).start();
    }
}
