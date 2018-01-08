package com.taylor.controller;

import com.taylor.entity.StockData;
import com.taylor.service.StockDataService;
import com.taylor.service.impl.RedisServiceImpl;
import com.taylor.stock.strategy.KdjStrategy;
import com.taylor.stock.strategy.MacdStrategy;
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
    public Set<String> getRedisKeys(HttpServletRequest request, HttpServletResponse response) {
        return redisService.getKeys();
    }

    @ResponseBody
    @RequestMapping("redis_value")
    public String getRedisKeys(@RequestParam(name = "stockCode") String stockCode, HttpServletRequest request, HttpServletResponse response) {
        return redisService.get(stockCode);
    }

    @ResponseBody
    @RequestMapping("/macd/start")
    public String queryStockDataWithMacd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        stockDataService.processData(new MacdStrategy("macd指标算法"));
        return "正在分析，请耐心等待";
    }

    @ResponseBody
    @RequestMapping("/kdj/start")
    public String queryStockDataByKdj(HttpServletRequest request, HttpServletResponse response) throws IOException {
        stockDataService.processData(new KdjStrategy("kdj指标算法"));
        return "正在分析，请耐心等待";
    }

    public void catchStock(List<StockData> result) {
        for (StockData stockData : result) {
            System.out.println(stockData.getStockCode());
            redisService.put(stockData.getStockCode(), stockData.getStockName(), -1);
        }
    }
}
