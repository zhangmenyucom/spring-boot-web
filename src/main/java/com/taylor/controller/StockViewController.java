package com.taylor.controller;

import com.taylor.common.StockUtils;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockOnShelf;
import com.taylor.service.RecmdStockService;
import com.taylor.service.StockDataService;
import com.taylor.service.StockOnShelfService;
import com.taylor.stock.common.StrategyEnum;
import com.taylor.stock.request.OnShelfUpdator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/26 1:30
 */
@Controller
@RequestMapping("/stock/view/")
public class StockViewController {

    @Autowired
    private RecmdStockService recmdStockService;

    @Autowired
    private StockOnShelfService stockOnShelfService;

    @Autowired
    private StockDataService stockDataService;

    @RequestMapping("/recmd/{type}")
    public String recomand(Map<String, Object> map, @PathVariable(name = "type") int type, @RequestParam(defaultValue = "", name = "recordTime") String recordTime, HttpServletRequest request) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        HttpSession session = request.getSession();
        List<String> listDate = new ArrayList<>();
        Date now = new Date();
        for (int i = 5; i >= 0; i--) {
            listDate.add(sdf.format(StockUtils.getDateAfter(now, -i)));
        }
        RecmdStock recmdStock = new RecmdStock();
        if ("".equals(recordTime) && session.getAttribute("recordTime") == null) {
            recmdStock.setRecordTime(new Date());
        } else if (!"".equals(recordTime)) {
            recmdStock.setRecordTime(sdf.parse(recordTime));
        } else {
            recmdStock.setRecordTime((Date) session.getAttribute("recordTime"));
        }
        session.setAttribute("recordTime", recmdStock.getRecordTime());
        List<RecmdStock> recmdStocks;
        if (type == StrategyEnum.TYPE15.getCode()) {
            recmdStocks = recmdStockService.getRecmdStockByCountTime(recmdStock.getRecordTime());
        } else {
            if (type != -1) {
                recmdStock.setStrategyType(type);
            }
            recmdStocks = recmdStockService.find(recmdStock);
        }
        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(new StockOnShelf());
        Map<String, Object> onshelfMap = new HashMap<>();
        for (StockOnShelf stockOnShelf : stockOnShelves) {
            onshelfMap.put(stockOnShelf.getStockCode(), stockOnShelf);
        }
        map.put("recordTime", sdf.format(recmdStock.getRecordTime()));
        map.put("type", type);
        map.put("recmdList", recmdStocks);
        map.put("strategyName", StrategyEnum.getEnumValue(type));
        map.put("listDate", listDate);
        map.put("onshelfMap", onshelfMap);
        return "/recmd";
    }

    @RequestMapping("/check_result")
    public String checkResult(Map<String, Object> map) {
        recmdStockService.checkResult();
        List<RecmdStock> recmdStocks = recmdStockService.find(new RecmdStock());
        map.put("recmdList", recmdStocks);
        return "/recmd";
    }

    @RequestMapping("/shelf")
    public String shlef(Map<String, Object> map) {
        StockOnShelf stockOnShelfQuery = new StockOnShelf();
        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(stockOnShelfQuery);
        map.put("stockOnShelves", stockOnShelves);
        return "/shelf";
    }

    @RequestMapping("/updateShelf")
    public String updateShlef(Map<String, Object> map) {
        if (OnShelfUpdator.FLAG == 0) {
            StockOnShelf stockOnShelfUpdate = new StockOnShelf();
            stockOnShelfService.updateSelf(stockOnShelfUpdate);
        }
        StockOnShelf stockOnShelfQuery = new StockOnShelf();
        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(stockOnShelfQuery);
        map.put("stockOnShelves", stockOnShelves);
        return "/shelf";
    }
}
