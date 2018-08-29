package com.taylor.stock.request;

import com.taylor.api.ApiClient;
import com.taylor.common.CommonRequest;
import com.taylor.common.MailUtils;
import com.taylor.entity.StockOnShelf;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.service.StockOnShelfService;
import lombok.Data;

import java.text.DecimalFormat;
import java.util.List;

import static com.taylor.common.ConstantsInits.YIDONG_MONITOR;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/31 10:31
 */
@Data
public class OnShelfUpdator extends Thread {

    public static volatile int FLAG = 0;

    private StockOnShelfService stockOnShelfService;

    private StockOnShelf stockOnShelfQuery;

    public OnShelfUpdator(StockOnShelf stockOnShelfQuery, StockOnShelfService stockOnShelfService) {
        this.stockOnShelfService = stockOnShelfService;
        this.stockOnShelfQuery = stockOnShelfQuery;
    }

    @Override
    public void run() {
        DecimalFormat df = new DecimalFormat("0.00");
        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(stockOnShelfQuery);
        for (StockOnShelf stockOnShelf : stockOnShelves) {
            StockPanKouData stockPanKouData = ApiClient.getPanKouData(stockOnShelf.getStockCode());
            Double discount = (stockPanKouData.getCurrentPrice() - stockOnShelf.getCurrentPrice()) / stockOnShelf.getCurrentPrice();
            if (YIDONG_MONITOR == 1 && Math.abs(discount) > 0.01) {
                MailUtils.sendMail(stockOnShelf.getStockName() + (discount > 0.0d ? "拉升" : "抛盘") + df.format(discount * 100) + "%现涨" +df.format(stockPanKouData.getUpDownMountPercent()) + "%", "");
            }
            stockOnShelf.setCurrentPrice(stockPanKouData.getCurrentPrice());
            stockOnShelf.setNetRatio(stockPanKouData.getUpDownMountPercent());
            stockOnShelf.setStockName(stockPanKouData.getStockName());
            stockOnShelfService.update(stockOnShelf);
        }
    }
}
