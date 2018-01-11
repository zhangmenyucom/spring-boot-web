package com.taylor.controller;

import com.taylor.service.StockBaseInfoService;
import com.taylor.service.StockDataService;
import com.taylor.stock.strategy.MacdStrategy;
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
        stockDataService.processData(new MacdStrategy("macd指标算法"));
        return "正在分析，请耐心等待";
    }

}
