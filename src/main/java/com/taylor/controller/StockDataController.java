package com.taylor.controller;

import com.taylor.common.Constants;
import com.taylor.entity.StockData;
import com.taylor.service.RecmdStockService;
import com.taylor.service.StockDataService;
import com.taylor.stock.request.QueryStockDataWithGet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.taylor.common.ConstantsInits.STOCK_CODE_LIST_SH;
import static com.taylor.common.ConstantsInits.STOCK_CODE_LIST_SZ;

/**
 * @author Administrator
 */
@RequestMapping("/stock")
@Controller
@Slf4j
public class StockDataController extends BaseAction {

    @Autowired
    private StockDataService stockDataService;

    @Autowired
    private RecmdStockService recmdStockService;

    @ResponseBody
    @RequestMapping("/query")
    public List<StockData> queryStockData(StockData stockData, HttpServletRequest request, HttpServletResponse response) {
        log.debug("这只是一个测试");
        return stockDataService.find(stockData);
    }

    @ResponseBody
    @RequestMapping("/start")
    public String queryStockData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new QueryStockDataWithGet(recmdStockService, STOCK_CODE_LIST_SH).start();
        new QueryStockDataWithGet(recmdStockService, STOCK_CODE_LIST_SZ).start();
        return "正在分析，请耐心等待";
    }
}
