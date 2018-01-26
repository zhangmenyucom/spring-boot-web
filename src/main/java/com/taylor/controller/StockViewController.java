package com.taylor.controller;

import com.taylor.entity.RecmdStock;
import com.taylor.service.RecmdStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/recmd")
    public String Recomand(Map<String, Object> map) {
        System.out.println(123123);
        List<RecmdStock> recmdStocks = recmdStockService.find(new RecmdStock());
        map.put("recmdList", recmdStocks);
        return "/recmd";
    }
}
