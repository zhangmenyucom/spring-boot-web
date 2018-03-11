package com.taylor.controller;

import com.taylor.common.StockUtils;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockOnShelf;
import com.taylor.service.RecmdStockService;
import com.taylor.service.StockOnShelfService;
import com.taylor.stock.common.StrategyEnum;
import com.taylor.stock.request.OnShelfUpdator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/recmd/{type}")
    public String recomand(Map<String, Object> map, @PathVariable(name = "type") int type, String recordTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        List<String> listDate = new ArrayList<>();
        Date now = new Date();
        for (int i = 5; i >= 0; i--) {
            listDate.add(sdf.format(StockUtils.getDateAfter(now, -i)));
        }
        RecmdStock recmdStock = new RecmdStock();
        if (recordTime == null) {
            recmdStock.setRecordTime(new Date());
        } else {
            recmdStock.setRecordTime(sdf.parse(recordTime));
        }
        List<RecmdStock> recmdStocks;
        if (type == StrategyEnum.TYPE15.getCode()) {
            recmdStocks = recmdStockService.getRecmdStockByCountTime();
        } else {
            if (type != -1) {
                recmdStock.setStrategyType(type);
            }
            recmdStocks = recmdStockService.find(recmdStock);
        }
        map.put("type", type);
        map.put("recmdList", recmdStocks);
        map.put("strategyName", StrategyEnum.getEnumValue(type));
        map.put("listDate", listDate);
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
        if (OnShelfUpdator.a == 0) {
            StockOnShelf stockOnShelfUpdate = new StockOnShelf();
            stockOnShelfService.updateSelf(stockOnShelfUpdate);
        }
        StockOnShelf stockOnShelfQuery = new StockOnShelf();
        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(stockOnShelfQuery);
        map.put("stockOnShelves", stockOnShelves);
        return "/shelf";
    }

}
