package com.taylor.service.impl;

import com.taylor.common.AbstractCrudService;
import com.taylor.common.Constants;
import com.taylor.common.JsonUtil;
import com.taylor.dao.RecmdStockDao;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockBaseInfo;
import com.taylor.service.RecmdStockService;
import com.taylor.stock.request.QueryStockBaseDataRequest;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class RecmdStockServiceImpl extends AbstractCrudService<RecmdStock, RecmdStock, RecmdStockDao> implements RecmdStockService {


    @Override
    public void checkResult() {
        HttpMethodBase method = new GetMethod(Constants.METHOD_URL_STOCK_BASE_INFO);
        List<RecmdStock> recmdStocks = this.find(new RecmdStock());
        for (RecmdStock recmdStock : recmdStocks) {
            List<StockBaseInfo> stockBaseInfos = QueryStockBaseDataRequest.queryStockBaseInfo(recmdStock.getStockCode(), method);
            if (!stockBaseInfos.isEmpty()) {
                stockBaseInfos.get(0).setId(recmdStock.getId());
                this.updateUpDownRatio(stockBaseInfos.get(0));
            }
        }
    }

    public int updateUpDownRatio(StockBaseInfo stockBaseInfo) {
        return this.getDao().updateRecmdTodayUpDownRatio(stockBaseInfo);
    }
}
