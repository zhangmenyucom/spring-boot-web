package com.taylor.controller;

import com.taylor.service.StockBaseInfoService;
import com.taylor.service.StockDataService;
import com.taylor.stock.strategy.MacdStrategy;
import com.taylor.stock.strategy.ShiZiStrategy;
import com.taylor.stock.strategy.TMacdStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 */
@RequestMapping("/stock/macd")
@Controller
@Slf4j
public class StockDataMacdController extends BaseAction {

    @Autowired
    private StockBaseInfoService stockBaseInfoService;

    @Autowired
    private StockDataService stockDataService;


    @ResponseBody
    @RequestMapping("/start")
    public String queryStockDataWithMacd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        stockDataService.processData(new MacdStrategy());
        return "正在分析，请耐心等待";
    }

    @ResponseBody
    @RequestMapping("/shizi/start")
    public String queryShiZiWithMacd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        stockDataService.processData(new ShiZiStrategy());
        return "正在分析，请耐心等待";
    }
    @ResponseBody
    @RequestMapping("/tzi/start")
    public String queryTZiWithMacd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        stockDataService.processData(new TMacdStrategy());
        return "正在分析，请耐心等待";
    }

}
