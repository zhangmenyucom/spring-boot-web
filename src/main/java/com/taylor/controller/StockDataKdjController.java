package com.taylor.controller;

import com.taylor.service.StockDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 */
@RequestMapping("/stock/kdj")
@Controller
@Slf4j
public class StockDataKdjController extends BaseAction {

    @Autowired
    private StockDataService stockDataService;
}