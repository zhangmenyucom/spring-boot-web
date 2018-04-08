package com.taylor.stock.request;

import com.taylor.common.Constants;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.stock.TencentTodayBaseInfo;
import com.taylor.service.RecmdStockService;
import lombok.Data;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;

import java.util.List;

import static com.taylor.common.CommonRequest.getStckTodayBaseInfo;

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
        HttpMethodBase method = new GetMethod(Constants.METHOD_URL_STOCK_BASE_INFO);
        List<RecmdStock> recmdStocks = recmdStockService.find(recmdStock);
        for (RecmdStock recmdStock : recmdStocks) {
            TencentTodayBaseInfo stckTodayBaseInfo = getStckTodayBaseInfo(recmdStock.getStockCode().toLowerCase());
            if(stckTodayBaseInfo!=null){
                stckTodayBaseInfo.setStockCode(recmdStock.getStockCode());
                recmdStockService.updateUpDownRatio(stckTodayBaseInfo);
            }
        }
    }
}
