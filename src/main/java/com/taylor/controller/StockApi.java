package com.taylor.controller;

import com.taylor.common.ApiResponse;
import com.taylor.common.CommonRequest;
import com.taylor.common.ErrorCode;
import com.taylor.entity.StockOnShelf;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.service.RecmdStockService;
import com.taylor.service.StockDataService;
import com.taylor.service.StockOnShelfService;
import com.taylor.service.impl.RedisServiceImpl;
import com.taylor.stock.request.QueryStockDayDataRequest;
import com.taylor.stock.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.taylor.common.ConstantsInits.STOCK_ON_MONITOR_LIST;
import static com.taylor.common.ConstantsInits.STOCK_ON_MONITOR_MAP;

/**
 * @author Administrator
 */
@RequestMapping("/stock/api")
@Controller
@Slf4j
public class StockApi extends BaseAction {

    @Autowired
    private StockDataService stockDataService;

    @Autowired
    private RedisServiceImpl<String> redisService;

    @Autowired
    private RecmdStockService recmdStockService;

    @Autowired
    private StockOnShelfService stockOnShelfService;


    @ResponseBody
    @RequestMapping("/remove_choose")
    public ApiResponse<Boolean> remveStockOnShelf(@RequestBody StockOnShelf stockOnShelf, HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<Boolean> result = new ApiResponse<>(ErrorCode.FAILED);
        if (stockOnShelfService.del(stockOnShelf) != 0) {
            result.setErrorNo(ErrorCode.SUCCESS);
            result.setData(Boolean.TRUE);
        }
        if (STOCK_ON_MONITOR_MAP.get(stockOnShelf.getStockCode()) != null) {
            updateMonitorCheche();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/update_monitor")
    public ApiResponse<Boolean> updateMonitor(@RequestBody StockOnShelf stockOnShelf, HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<Boolean> result = new ApiResponse<>(ErrorCode.FAILED);
        if (stockOnShelfService.update(stockOnShelf) != 0) {
            result.setErrorNo(ErrorCode.SUCCESS);
            result.setData(Boolean.TRUE);
        }
        if (STOCK_ON_MONITOR_MAP.get(stockOnShelf.getStockCode()) != null) {
            updateMonitorCheche();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/add_choose")
    public ApiResponse<Boolean> addStockOnShelf(@RequestBody StockOnShelf stockOnShelf, HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<Boolean> result = new ApiResponse<>(ErrorCode.FAILED);
        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(stockOnShelf);
        if (stockOnShelves != null && !stockOnShelves.isEmpty()) {
            result.setErrorMsg("该股票已经存在自选中");
            return result;
        }
        StockPanKouData stockPanKouData = CommonRequest.getStockPanKouData(stockOnShelf.getStockCode());
        if (stockPanKouData != null) {
            stockOnShelf.setStockName(stockPanKouData.getStockName());
            stockOnShelf.setFocusPrice(stockPanKouData.getCurrentPrice());
            stockOnShelf.setCurrentPrice(stockPanKouData.getCurrentPrice());
            stockOnShelf.setNetRatio(stockPanKouData.getUpDownMountPercent());
            stockOnShelf.setStatus(0);
        }
        stockOnShelfService.saveSelective(stockOnShelf);
        result.setErrorNo(ErrorCode.SUCCESS);
        return result;
    }

    @ResponseBody
    @RequestMapping("/start_choose")
    public ApiResponse<Boolean> startChoose(HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
        ApiResponse<Boolean> result = new ApiResponse<>(ErrorCode.FAILED);
        QueryStockDayDataRequest.run_flag = 0;

      /*  ShiZiMacdStrategy shiZiMacdStrategy = new ShiZiMacdStrategy();*/
        BigYinLineStrategy bigYinLineStrategy = new BigYinLineStrategy();
      /*  Over5DayStrategy over5DayStrategy = new Over5DayStrategy();
        BigYinLineStrategy bigYinLineStrategy = new BigYinLineStrategy();
        BeiLiStrategy beiLiStrategy = new BeiLiStrategy();
        FiveOverTenStrategy fiveOverTenStrategy = new FiveOverTenStrategy();
        OverYaLiStrategy overYaLiStrategy = new OverYaLiStrategy();
        MacdOverZeroStrategy macdOverZeroStrategy = new MacdOverZeroStrategy();
        YiYangChuanSanXianStrategy yiYangChuanSanXianStrategy = new YiYangChuanSanXianStrategy();
        MacdRedFor3DayStrategy macdRedFor3DayStrategy = new MacdRedFor3DayStrategy();
        shiZiMacdStrategy.setNext(over5DayStrategy);
        over5DayStrategy.setNext(bigYinLineStrategy);
        bigYinLineStrategy.setNext(beiLiStrategy);
        beiLiStrategy.setNext(overYaLiStrategy);
        overYaLiStrategy.setNext(fiveOverTenStrategy);
        fiveOverTenStrategy.setNext(yiYangChuanSanXianStrategy);
        yiYangChuanSanXianStrategy.setNext(macdOverZeroStrategy);
        macdOverZeroStrategy.setNext(macdRedFor3DayStrategy);*/
        List<Integer> strategyTypeList = new ArrayList<>();
        /**清除当天及5天以外的数据**/
        IStrategy iStrategy = bigYinLineStrategy;
        do {
            strategyTypeList.add(iStrategy.getStrategyEnum().getCode());
            iStrategy = iStrategy.getNext();
        } while (iStrategy != null);
        recmdStockService.delByStrategyList(strategyTypeList);
        stockDataService.processData(bigYinLineStrategy);
        result.setErrorNo(ErrorCode.SUCCESS);
        return result;
    }

    private void updateMonitorCheche() {
        StockOnShelf stockOnShelfQuery = new StockOnShelf();
        stockOnShelfQuery.setStatus(1);
        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(stockOnShelfQuery);
        List<String> stockMonitor = new ArrayList<>();
        Map<String, String> stockMonitorMap = new HashMap<>(0);
        for (StockOnShelf stockOnShelf : stockOnShelves) {
            stockMonitor.add(stockOnShelf.getStockCode());
            stockMonitorMap.put(stockOnShelf.getStockCode(), stockOnShelf.getStockName());
        }
        STOCK_ON_MONITOR_LIST = stockMonitor;
        STOCK_ON_MONITOR_MAP = stockMonitorMap;
    }
}
