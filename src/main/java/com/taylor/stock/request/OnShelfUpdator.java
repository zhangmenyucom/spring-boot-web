package com.taylor.stock.request;

import com.taylor.common.CommonRequest;
import com.taylor.common.KLineTypeEnum;
import com.taylor.common.MailUtils;
import com.taylor.entity.StockOnShelf;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.entity.stock.kdj.KdjTimeBean;
import com.taylor.service.StockOnShelfService;
import lombok.Data;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/31 10:31
 */
@Data
public class OnShelfUpdator extends Thread {

    public static volatile int a = 0;

    private StockOnShelfService stockOnShelfService;

    private StockOnShelf stockOnShelfQuery;

    public OnShelfUpdator(StockOnShelf stockOnShelfQuery, StockOnShelfService stockOnShelfService) {
        this.stockOnShelfService = stockOnShelfService;
        this.stockOnShelfQuery = stockOnShelfQuery;
    }

    @Override
    public void run() {
        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(stockOnShelfQuery);
        for (StockOnShelf stockOnShelf : stockOnShelves) {
            StockPanKouData stockPanKouData = CommonRequest.getStockPanKouData(stockOnShelf.getStockCode());
            if(Math.abs((stockPanKouData.getCurrentPrice()-stockOnShelf.getCurrentPrice())/stockOnShelf.getCurrentPrice())>0.01){
                MailUtils.sendMail(stockOnShelf.getStockName()+"有异动请立即关注","");
            }
            stockOnShelf.setCurrentPrice(stockPanKouData.getCurrentPrice());
            stockOnShelf.setNetRatio(stockPanKouData.getUpDownMountPercent());
            stockOnShelf.setStockName(stockPanKouData.getStockName());
            stockOnShelfService.update(stockOnShelf);
        }
    }
}
