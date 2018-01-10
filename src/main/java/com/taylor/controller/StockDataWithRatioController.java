package com.taylor.controller;

import com.taylor.entity.RecmdStock;
import com.taylor.service.RecmdStockService;
import com.taylor.service.RecmdStockWithRatioService;
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
import java.util.List;

/**
 * @author Administrator
 */
@RequestMapping("/recmd/")
@Controller
@Slf4j
public class StockDataWithRatioController extends BaseAction {

    @Autowired
    private RecmdStockService recmdStockService;

    @Autowired
    private RecmdStockWithRatioService recmdStockWithRatioService;

    @ResponseBody
    @RequestMapping("/start/ratio")
    public String queryRecmdStock(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<RecmdStock> recmdStocks = recmdStockService.find(new RecmdStock());
        recmdStockWithRatioService.process(recmdStocks);
        return "正在分析，请耐心等待";
    }


}