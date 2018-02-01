package com.taylor.controller;

import com.taylor.entity.StockOnShelf;
import com.taylor.service.StockOnShelfService;
import com.taylor.stock.request.KdjMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        KdjMonitor.a = 0;
        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(new StockOnShelf());
        String stockStr="";
        for (StockOnShelf stockOnShelf : stockOnShelves) {
            new KdjMonitor(stockOnShelf.getStockCode()).start();
            stockStr+=" "+stockOnShelf.getStockName();
        }
        return "正在监控下列股票"+stockStr;
    }

    @RequestMapping("/stop")
    public String helloJsp(Map<String, Object> map) {
        KdjMonitor.a = 1;
        return "已停止";
    }
}