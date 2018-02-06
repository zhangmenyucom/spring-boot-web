package com.taylor.service.impl;

import com.taylor.common.AbstractCrudService;
import com.taylor.common.Constants;
import com.taylor.common.JsonUtil;
import com.taylor.dao.RecmdStockDao;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockBaseInfo;
import com.taylor.service.RecmdStockService;
import com.taylor.stock.request.QueryStockBaseDataRequest;
import com.taylor.stock.request.RecmdUpdator;
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
        new RecmdUpdator(new RecmdStock(), this).start();

    }

    @Override
    public int updateUpDownRatio(StockBaseInfo stockBaseInfo) {
        return this.getDao().updateRecmdTodayUpDownRatio(stockBaseInfo);
    }
}
