package com.taylor.stock.request;

import com.taylor.api.ApiClient;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.service.RecmdStockService;
import lombok.Data;

import java.util.List;


/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/31 10:31
 */
@Data
public class RecmdUpdator extends Thread {

    public static volatile int a = 0;

    private RecmdStockService recmdStockService;

    private RecmdStock recmdStock;

    public RecmdUpdator(RecmdStock recmdStock, RecmdStockService recmdStockService) {
        this.recmdStockService = recmdStockService;
        this.recmdStock = recmdStock;
    }

    @Override
    public void run() {
        List<RecmdStock> recmdStocks = recmdStockService.find(recmdStock);
        for (RecmdStock recmdStock : recmdStocks) {
            StockPanKouData stockPanKouData = ApiClient.getPanKouData(recmdStock.getStockCode().toLowerCase());
            if(stockPanKouData!=null){
                stockPanKouData.setStockCode(recmdStock.getStockCode());
                recmdStockService.updateUpDownRatio(stockPanKouData);
            }
        }
    }
}
