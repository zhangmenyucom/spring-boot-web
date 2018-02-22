package com.taylor.service.impl;

import com.taylor.common.AbstractCrudService;
import com.taylor.common.Constants;
import com.taylor.dao.StockOnShelfDao;
import com.taylor.entity.StockOnShelf;
import com.taylor.entity.stock.TimeLineBean;
import com.taylor.entity.stock.TimeStockDataResponse;
import com.taylor.service.StockOnShelfService;
import com.taylor.stock.request.OnShelfUpdator;
import com.taylor.stock.request.QueryStockTimeDataRequest;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class StockOnShelfServiceImpl extends AbstractCrudService<StockOnShelf, StockOnShelf, StockOnShelfDao> implements StockOnShelfService {
    @Override
    public void listen() {
        StockOnShelf stockOnShelf = new StockOnShelf();
        stockOnShelf.setStatus(1);
        List<StockOnShelf> stockOnShelves = this.find(stockOnShelf);
        GetMethod method = new GetMethod(Constants.METHOD_URL_STOCK_TIME_INFO);
        for (StockOnShelf onShelf : stockOnShelves) {
            TimeStockDataResponse timeStockDataResponse = QueryStockTimeDataRequest.queryStockBaseInfo(onShelf.getStockCode().toLowerCase(), method);
            if (timeStockDataResponse != null) {
                List<TimeLineBean> timeLines = timeStockDataResponse.getTimeLine();
                StockOnShelf updateBean = new StockOnShelf();
                updateBean.setId(onShelf.getId());
                onShelf.setCurrentPrice(timeLines.get(timeLines.size() - 1).getPrice());
                this.update(onShelf);
            }
        }
    }

    @Override
    public void updateSelf(StockOnShelf stockOnShelfQuery) {
        new OnShelfUpdator(stockOnShelfQuery, this).start();
    }
}
