package com.taylor.controller;

import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockOnShelf;
import com.taylor.service.RecmdStockService;
import com.taylor.service.StockOnShelfService;
import com.taylor.stock.request.OnShelfUpdator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("/recmd")
    public String recomand(Map<String, Object> map) {
        List<RecmdStock> recmdStocks = recmdStockService.find(new RecmdStock());
        map.put("recmdList", recmdStocks);
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
        if(OnShelfUpdator.a==0){
            StockOnShelf stockOnShelfUpdate = new StockOnShelf();
            stockOnShelfService.updateSelf(stockOnShelfUpdate);
        }
        StockOnShelf stockOnShelfQuery = new StockOnShelf();
        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(stockOnShelfQuery);
        map.put("stockOnShelves", stockOnShelves);
        return "/shelf";
    }

}
