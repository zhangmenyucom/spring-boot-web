package com.taylor.stock.request;

import com.taylor.common.CommonRequest;
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
        this.stockOnShelfService=stockOnShelfService;
        this.stockOnShelfQuery=stockOnShelfQuery;
    }

    @Override
    public void run() {
        while (a == 0) {
            List<StockOnShelf> stockOnShelves = stockOnShelfService.find(stockOnShelfQuery);
            for (StockOnShelf stockOnShelf : stockOnShelves) {
                List<KdjTimeBean> kdjTimeList = KdjTimeDataRequest.getKdjTimeList(stockOnShelf.getStockCode());
                KdjTimeBean kdjTimeBean = kdjTimeList.get(kdjTimeList.size() - 1);
                StockPanKouData stockPanKouData = CommonRequest.getStockPanKouData(stockOnShelf.getStockCode());
                stockOnShelf.setCurrentPrice(stockPanKouData.getCurrentPrice());
                stockOnShelf.setNetRatio(stockPanKouData.getUpDownMountPercent());
                stockOnShelf.setFiveMiniRatio(kdjTimeBean.getC_px_change_percent());
                stockOnShelfService.update(stockOnShelf);
            }
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
