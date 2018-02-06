package com.taylor.controller;

import com.taylor.common.KLineTypeEnum;
import com.taylor.entity.StockOnShelf;
import com.taylor.service.StockOnShelfService;
import com.taylor.stock.request.KdjFiveMiniMonitor;
import com.taylor.stock.request.KdjOneMiniMonitor;
import com.taylor.stock.request.OnShelfUpdator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 测试
 *
 * @author Taylor
 * @version v.0.1
 */
@RestController
@RequestMapping("/monitor/")
public class MonitorController {
    private static volatile int a = 0;

    @Autowired
    private StockOnShelfService stockOnShelfService;

    @RequestMapping("/start")
    public String startMonitor() {
        KdjFiveMiniMonitor.a = 0;
        StockOnShelf stockOnShelfQuery = new StockOnShelf();
        stockOnShelfQuery.setStatus(1);
        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(stockOnShelfQuery);
        String stockStr = "";
        for (StockOnShelf stockOnShelf : stockOnShelves) {
            new KdjFiveMiniMonitor(stockOnShelf.getStockCode(), KLineTypeEnum.FIVE_MINI).start();
            stockStr += " " + stockOnShelf.getStockName();
        }
        return "正在监控下列股票" + stockStr;
    }

    @RequestMapping("/stop")
    public String helloJsp(Map<String, Object> map) {
        KdjFiveMiniMonitor.a = 1;
        KdjOneMiniMonitor.a = 1;
        return "已停止";
    }

    @RequestMapping("/onshelf/update")
    public String updateStart(Map<String, Object> map) {
        StockOnShelf stockOnShelfQuery = new StockOnShelf();
        stockOnShelfQuery.setStatus(1);
        stockOnShelfService.updateSelf(stockOnShelfQuery);
        return "正在更新中....";
    }

    @RequestMapping("/onshelf/stop")
    public String updateStop(Map<String, Object> map) {
        OnShelfUpdator.a = 1;
        return "停止更新....";
    }
}