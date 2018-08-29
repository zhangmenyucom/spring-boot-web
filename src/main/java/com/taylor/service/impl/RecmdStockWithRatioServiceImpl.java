package com.taylor.service.impl;

import com.taylor.api.ApiClient;
import com.taylor.common.AbstractCrudService;
import com.taylor.common.Constants;
import com.taylor.dao.RecmdStockWithRatioDao;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.RecmdWithRatioStock;
import com.taylor.service.RecmdStockWithRatioService;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class RecmdStockWithRatioServiceImpl extends AbstractCrudService<RecmdWithRatioStock, RecmdWithRatioStock, RecmdStockWithRatioDao> implements RecmdStockWithRatioService {

    @Override
    public void process(List<RecmdStock> recmdStocks) {
        double redioOver = 1.0;
        GetMethod method = new GetMethod(Constants.METHOD_URL_STOCK_BASE_INFO);
        for (RecmdStock recmdStock : recmdStocks) {
            double turnoverRatio = ApiClient.getBaseStockInfo(recmdStock.getStockCode()).getTurnoverRatio().doubleValue();
            if (turnoverRatio >= redioOver) {
                RecmdWithRatioStock recmdWithRatioStock = new RecmdWithRatioStock();
                recmdWithRatioStock.setRecmdOperate(recmdStock.getRecmdOperate());
                recmdWithRatioStock.setStockCode(recmdStock.getStockCode());
                recmdWithRatioStock.setStockName(recmdStock.getStockName());
                recmdWithRatioStock.setTurnoverRatio(turnoverRatio);
                recmdWithRatioStock.setStrategy("换手率大于" + redioOver);
                this.save(recmdWithRatioStock);
            }
        }
    }
}
