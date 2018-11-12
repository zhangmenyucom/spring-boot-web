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

import static com.taylor.common.ConstantsInits.*;

/**
 * 测试
 *
 * @author Taylor
 * @version v.0.1
 */
@RestController
@RequestMapping("/monitor/")
public class MonitorController {

    @Autowired
    private StockOnShelfService stockOnShelfService;


    @RequestMapping("/start_yidong")
    public String startYiDongMonitor() {
        if (YIDONG_MONITOR == 1) {
            return "已经开启，无需再次开启监控";
        }
        YIDONG_MONITOR = 1;
        return "已经开启异动监控";
    }

    @RequestMapping("/stop_yidong")
    public String stopYiDongMonitor() {
        if (YIDONG_MONITOR == 0) {
            return "已经关闭，无需再次关闭监控";
        }
        YIDONG_MONITOR = 0;
        return "已经关闭异动监控";
    }
    @RequestMapping("/start_cost")
    public String startCostMonitor() {
        if (COST_MONITOR == 1) {
            return "已经成本监控，无需再次开启监控";
        }
        COST_MONITOR = 1;
        return "已经成本监控";
    }

    @RequestMapping("/stop_cost")
    public String stopCostMonitor() {
        if (COST_MONITOR == 0) {
            return "已经关闭成本监控，无需再次关闭监控";
        }
        COST_MONITOR = 0;
        return "已经关闭成本监控";
    }


    @RequestMapping("/start")
    public String startMonitor() {
        if (KdjFiveMiniMonitor.a == 1) {
            return "已经开启，无需再次开启监控";
        }
        KdjFiveMiniMonitor.a = 1;
        StockOnShelf stockOnShelfQuery = new StockOnShelf();
        stockOnShelfQuery.setStatus(1);
        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(stockOnShelfQuery);
        if (STOCK_ON_MONITOR_LIST.isEmpty()) {
            for (StockOnShelf stockOnShelf : stockOnShelves) {
                STOCK_ON_MONITOR_LIST.add(stockOnShelf.getStockCode());
                STOCK_ON_MONITOR_MAP.put(stockOnShelf.getStockCode(), stockOnShelf.getStockName());
            }
        }
        String stockStr = "";
        for (String stockCode : STOCK_ON_MONITOR_LIST) {
            stockStr += " " + STOCK_ON_MONITOR_MAP.get(stockCode);
        }
        new KdjFiveMiniMonitor(KLineTypeEnum.FIVE_MINI).start();
        return "kdj 5分钟线，每90秒执行一次,正在监控下列股票" + stockStr;
    }

    @RequestMapping("/startOne")
    public String startOneMonitor() {
        if (KdjOneMiniMonitor.a == 1) {
            return "已经开启，无需再次开启监控";
        }
        KdjOneMiniMonitor.a = 1;
        StockOnShelf stockOnShelfQuery = new StockOnShelf();
        stockOnShelfQuery.setStatus(1);
        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(stockOnShelfQuery);

        if (STOCK_ON_MONITOR_LIST.isEmpty()) {
            for (StockOnShelf stockOnShelf : stockOnShelves) {
                STOCK_ON_MONITOR_LIST.add(stockOnShelf.getStockCode());
                STOCK_ON_MONITOR_MAP.put(stockOnShelf.getStockCode(), stockOnShelf.getStockName());
            }
        }
        String stockStr = "";
        for (String stockCode : STOCK_ON_MONITOR_LIST) {
            stockStr += " " + STOCK_ON_MONITOR_MAP.get(stockCode);
        }
        new KdjOneMiniMonitor(KLineTypeEnum.ONE_MINI).start();
        return "kdj 1分钟线，每30秒执行一次,正在监控下列股票" + stockStr;
    }

    @RequestMapping("/stop")
    public String helloJsp(Map<String, Object> map) {
        KdjFiveMiniMonitor.a = 0;
        return "5分钟kjd已停止监控";
    }

    @RequestMapping("/stopOne")
    public String stopOne(Map<String, Object> map) {
        KdjOneMiniMonitor.a = 0;
        return "1分钟kjd已停止监控";
    }

    @RequestMapping("/onshelf/update")
    public String updateStart(Map<String, Object> map) {
        StockOnShelf stockOnShelfQuery = new StockOnShelf();
        stockOnShelfService.updateSelf(stockOnShelfQuery);
        return "正在更新中....";
    }

    @RequestMapping("/onshelf/stop")
    public String updateStop(Map<String, Object> map) {
        OnShelfUpdator.FLAG = 1;
        return "停止更新....";
    }
}