package com.taylor.controller;

import com.taylor.service.StockDataService;
import com.taylor.stock.strategy.Kdj10Strategy;
import com.taylor.stock.strategy.Kdj510Strategy;
import com.taylor.stock.strategy.Kdj5Strategy;
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
@RequestMapping("/stock/kdj")
@Controller
@Slf4j
public class StockDataKdjController extends BaseAction {

    @Autowired
    private StockDataService stockDataService;

    /**
     * 两天kdj差为5
     **/
    @ResponseBody
    @RequestMapping("/start/5")
    public String queryStockDataByKdj5(HttpServletRequest request, HttpServletResponse response) throws IOException {
        stockDataService.processData(new Kdj5Strategy("kdj5指标算法"));
        return "正在分析，请耐心等待";
    }

    /**
     * 两天kdj差为10
     **/
    @ResponseBody
    @RequestMapping("/start/10")
    public String queryStockDataByKdj10(HttpServletRequest request, HttpServletResponse response) throws IOException {
        stockDataService.processData(new Kdj10Strategy("kdj10指标算法"));
        return "正在分析，请耐心等待";
    }

    /**
     * 两天kdj差为510
     **/
    @ResponseBody
    @RequestMapping("/start/510")
    public String queryStockDataByKdj510(HttpServletRequest request, HttpServletResponse response) throws IOException {
        stockDataService.processData(new Kdj510Strategy("kdj510指标算法"));
        return "正在分析，请耐心等待";
    }
}