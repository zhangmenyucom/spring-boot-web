package com.taylor.controller;

import com.taylor.entity.StockData;
import com.taylor.service.RecmdStockService;
import com.taylor.service.StockDataService;
import com.taylor.service.impl.RedisServiceImpl;
import com.taylor.stock.request.ProcessCountor;
import com.taylor.stock.request.QueryStockDataWithGet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private RedisServiceImpl<String> redisService;

    @ResponseBody
    @RequestMapping("/query")
    public List<StockData> queryStockData(StockData stockData, HttpServletRequest request, HttpServletResponse response) {
        log.debug("这只是一个测试");
        List<StockData> result = stockDataService.find(stockData);
        catchStock(result);
        return result;
    }

    @ResponseBody
    @RequestMapping("/redis_keys")
    public Set<String> getRedisKeys( HttpServletRequest request, HttpServletResponse response) {
        return redisService.getKeys();
    }
    @ResponseBody
    @RequestMapping("redis_value")
    public String getRedisKeys(@RequestParam(name="stockCode") String stockCode, HttpServletRequest request, HttpServletResponse response) {
        return redisService.get(stockCode);
    }

    @ResponseBody
    @RequestMapping("/start")
    public String queryStockData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new QueryStockDataWithGet(redisService,recmdStockService, STOCK_CODE_LIST_SH.subList(0, STOCK_CODE_LIST_SH.size() / 4)).start();
        new QueryStockDataWithGet(redisService,recmdStockService, STOCK_CODE_LIST_SH.subList(STOCK_CODE_LIST_SH.size() / 4 + 1, STOCK_CODE_LIST_SH.size()/2)).start();
        new QueryStockDataWithGet(redisService,recmdStockService, STOCK_CODE_LIST_SH.subList(STOCK_CODE_LIST_SH.size()/2+1,STOCK_CODE_LIST_SH.size()*3 / 4)).start();
        new QueryStockDataWithGet(redisService,recmdStockService, STOCK_CODE_LIST_SH.subList(STOCK_CODE_LIST_SH.size()*3/4+1 , STOCK_CODE_LIST_SH.size())).start();

        new QueryStockDataWithGet(redisService,recmdStockService, STOCK_CODE_LIST_SZ.subList(0, STOCK_CODE_LIST_SZ.size() / 4)).start();
        new QueryStockDataWithGet(redisService,recmdStockService, STOCK_CODE_LIST_SZ.subList(STOCK_CODE_LIST_SZ.size() / 4 + 1, STOCK_CODE_LIST_SZ.size()/2)).start();
        new QueryStockDataWithGet(redisService,recmdStockService, STOCK_CODE_LIST_SZ.subList(STOCK_CODE_LIST_SZ.size()/2+1,STOCK_CODE_LIST_SZ.size()*3 / 4)).start();
        new QueryStockDataWithGet(redisService,recmdStockService, STOCK_CODE_LIST_SZ.subList(STOCK_CODE_LIST_SZ.size()*3/4+1 , STOCK_CODE_LIST_SZ.size())).start();

        new ProcessCountor().start();
        return "正在分析，请耐心等待";
    }

    public void catchStock(List<StockData> result) {
        for (StockData stockData : result) {
            System.out.println(stockData.getStockCode());
            redisService.put(stockData.getStockCode(),stockData.getStockName(),-1);
        }
    }
}
