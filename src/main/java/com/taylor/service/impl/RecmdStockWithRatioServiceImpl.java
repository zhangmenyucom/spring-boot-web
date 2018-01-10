package com.taylor.service.impl;

import com.taylor.common.AbstractCrudService;
import com.taylor.common.Constants;
import com.taylor.dao.RecmdStockWithRatioDao;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.RecmdWithRatioStock;
import com.taylor.entity.stock.StockBaseInfo;
import com.taylor.entity.stock.query.StockBaseQueryBean;
import com.taylor.service.RecmdStockWithRatioService;
import com.taylor.stock.request.QueryStockBaseDataRequest;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class RecmdStockWithRatioServiceImpl extends  AbstractCrudService<RecmdWithRatioStock, RecmdWithRatioStock,RecmdStockWithRatioDao> implements RecmdStockWithRatioService {

    @Override
    public void process(List<RecmdStock> recmdStocks) {
        double redioOver=1.0;
        GetMethod method=new GetMethod(Constants.METHOD_URL_STOCK_BASE_INFO);
        for (RecmdStock recmdStock : recmdStocks) {
            StockBaseQueryBean stockQueryBean=new StockBaseQueryBean();
            stockQueryBean.setStock_code(recmdStock.getStockCode().toLowerCase());
            List<StockBaseInfo> stockBaseInfos = QueryStockBaseDataRequest.queryStockBaseInfo(stockQueryBean, method);
            if(stockBaseInfos!=null && !stockBaseInfos.isEmpty()){
                double turnoverRatio = stockBaseInfos.get(0).getTurnoverRatio();
                if(turnoverRatio>=redioOver){
                    RecmdWithRatioStock recmdWithRatioStock=new RecmdWithRatioStock();
                    recmdWithRatioStock.setRecmdOperate(recmdStock.getRecmdOperate());
                    recmdWithRatioStock.setStockCode(recmdStock.getStockCode());
                    recmdWithRatioStock.setStockName(recmdStock.getStockName());
                    recmdWithRatioStock.setTurnoverRatio(turnoverRatio);
                    recmdWithRatioStock.setStrategy("换手率大于"+redioOver);
                   this.save(recmdWithRatioStock);
                }
            }
        }
    }
}
