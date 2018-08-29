package com.taylor.service.impl;

import com.taylor.api.ApiClient;
import com.taylor.entity.StockBaseInfo;
import com.taylor.service.StockBaseInfoService;
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
    public StockBaseInfo getStockBaseInfo(String stockCode) {
          return ApiClient.getBaseStockInfo(stockCode);
    }

}
