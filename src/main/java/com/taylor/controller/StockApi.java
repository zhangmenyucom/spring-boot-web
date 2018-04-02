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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        return result;
    }
    @ResponseBody
    @RequestMapping("/update_monitor")
    public ApiResponse<Boolean> updateMonitor(@RequestBody StockOnShelf stockOnShelf, HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<Boolean> result = new ApiResponse<>(ErrorCode.FAILED);
        if (stockOnShelfService.update(stockOnShelf)!= 0) {
            result.setErrorNo(ErrorCode.SUCCESS);
            result.setData(Boolean.TRUE);
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
}
