package com.taylor.controller;

import com.taylor.common.Constants;
import com.taylor.service.StockDataService;
import com.taylor.stock.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    @RequestMapping("/start/kdiff/5")
    public String queryStockDataByKdj5(HttpServletRequest request, HttpServletResponse response) throws IOException {
        stockDataService.processData(new Kdj5Strategy("kdj5指标算法"));
        return "正在分析，请耐心等待";
    }

    /**
     * 两天kdj差为10
     **/
    @ResponseBody
    @RequestMapping("/start/kdiff/10")
    public String queryStockDataByKdj10(HttpServletRequest request, HttpServletResponse response) throws IOException {
        stockDataService.processData(new Kdj10Strategy("kdj10指标算法"));
        return "正在分析，请耐心等待";
    }

    /**
     * 两天kdj差为510
     **/
    @ResponseBody
    @RequestMapping("/start/kdiff/510")
    public String queryStockDataByKdj510(HttpServletRequest request, HttpServletResponse response) throws IOException {
        stockDataService.processData(new Kdj510Strategy("kdj510指标算法"));
        return "正在分析，请耐心等待";
    }
    /**
     * 两天kdj差为5并且换手率大于1
     **/
    @ResponseBody
    @RequestMapping("/start/betwenn/{kdiff}/{ratio}")
    public String queryStockDataByKdj5_1(HttpServletRequest request,@PathVariable("kdiff") Float kdiff,@PathVariable("ratio") Float ratio, HttpServletResponse response) throws IOException {
        stockDataService.processData(new KdjWithRatioStrategy("kdj小于"+kdiff+",ratio大于"+ratio,kdiff,ratio,new GetMethod(Constants.METHOD_URL_STOCK_BASE_INFO)));
        return "正在分析，请耐心等待";
    }
    /**
     * 两天kdj差为5并且换手率大于1
     **/
    @ResponseBody
    @RequestMapping("/start/over/{kdiff}/{ratio}")
    public String queryStockDataByKdjOver(HttpServletRequest request,@PathVariable("kdiff") Float kdiff,@PathVariable("ratio") Float ratio, HttpServletResponse response) throws IOException {
        stockDataService.processData(new KdjOverWithRatioStrategy("今天kdj差大于"+kdiff+",ratio大于"+ratio,kdiff,ratio,new GetMethod(Constants.METHOD_URL_STOCK_BASE_INFO)));
        return "正在分析，请耐心等待";
    }
}