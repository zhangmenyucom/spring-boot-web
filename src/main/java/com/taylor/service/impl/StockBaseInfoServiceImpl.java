package com.taylor.service.impl;

import com.taylor.entity.StockBaseInfo;
import com.taylor.service.StockBaseInfoService;
import com.taylor.stock.request.QueryStockBaseDataRequest;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 19:39
 */
@Service
public class StockBaseInfoServiceImpl implements StockBaseInfoService {

    @Override
    public List<StockBaseInfo> getStockBaseInfo(String stockCode) {
        GetMethod method = new GetMethod("https://gupiao.baidu.com/api/rails/stockbasicbatch");
        return QueryStockBaseDataRequest.queryStockBaseInfo(stockCode, method);
    }

}
