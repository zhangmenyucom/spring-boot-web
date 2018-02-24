package com.taylor.controller;

import com.taylor.service.StockDataService;
import com.taylor.stock.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 */
@RequestMapping("/stock/kdj")
@Controller
@Slf4j
public class StockDataKdjController extends BaseAction {

    @Autowired
    private StockDataService stockDataService;

    /**
     * 两天kdj差为5
     **/
    @ResponseBody
    @RequestMapping("/start/kdiff/5")
    public String queryStockDataByKdj5(HttpServletRequest request, HttpServletResponse response) throws IOException {
        stockDataService.processData(new Kdj5Strategy());
        return "正在分析，请耐心等待";
    }

    /**
     * 两天kdj差为10
     **/
    @ResponseBody
    @RequestMapping("/start/kdiff/10")
    public String queryStockDataByKdj10(HttpServletRequest request, HttpServletResponse response) throws IOException {
        stockDataService.processData(new Kdj10Strategy());
        return "正在分析，请耐心等待";
    }

    /**
     * 两天kdj差为510
     **/
    @ResponseBody
    @RequestMapping("/start/kdiff/510")
    public String queryStockDataByKdj510(HttpServletRequest request, HttpServletResponse response) throws IOException {
        stockDataService.processData(new Kdj510Strategy());
        return "正在分析，请耐心等待";
    }
    /**
     * 两天kdj差为5并且换手率大于1
     **/
    @ResponseBody
    @RequestMapping("/start/betwenn/{kdiff}/{ratio}")
    public String queryStockDataByKdj5_1(HttpServletRequest request,@PathVariable("kdiff") Float kdiff,@PathVariable("ratio") Float ratio, HttpServletResponse response) throws IOException {
        stockDataService.processData(new KdjWithRatioStrategy(kdiff,ratio));
        return "正在分析，请耐心等待";
    }
    /**
     * 两天kdj差为5并且换手率大于1
     **/
    @ResponseBody
    @RequestMapping("/start/over/{kdiff}/{ratio}")
    public String queryStockDataByKdjOver(HttpServletRequest request,@PathVariable("kdiff") Float kdiff,@PathVariable("ratio") Float ratio, HttpServletResponse response) throws IOException {
        stockDataService.processData(new KdjOverWithRatioStrategy(kdiff,ratio));
        return "正在分析，请耐心等待";
    }
    /**
     * 两天kdj比大于1
     **/
    @ResponseBody
    @RequestMapping("/start/over45/{kdiff}/{ratio}")
    public String queryStockDataByKdjOver45(HttpServletRequest request,@PathVariable("kdiff") Float kdiff,@PathVariable("ratio") Float ratio, HttpServletResponse response) throws IOException {
        stockDataService.processData(new KdjOver45duRatioStrategy(kdiff,ratio));
        return "正在分析，请耐心等待";
    }

    /**
     * 两天kdj比大于1
     **/
    @ResponseBody
    @RequestMapping("/start/week/{ratio}/do")
    public String queryStockDataByKdjWeek(HttpServletRequest request,@PathVariable("ratio") Float ratio, HttpServletResponse response) throws IOException {
        stockDataService.processData(new KdjWeekRatioStrategy(ratio));
        return "正在分析，请耐心等待";
    }

    /**
     * 两天kdj比大于1
     **/
    @ResponseBody
    @RequestMapping("/start/week/{ratio}/liangbi")
    public String queryStockDataByKdjWeekLiangbi(HttpServletRequest request,@PathVariable("ratio") Float ratio, HttpServletResponse response) throws IOException {
        stockDataService.processData(new KdjWeekRatioStrategyLiangBiOver1(ratio));
        return "正在分析，请耐心等待";
    }

    /**
     * 两天kdj比大于1
     **/
    @ResponseBody
    @RequestMapping("/start/week/{ratio}/main")
    public String queryStockDataByKdjWeekMain(HttpServletRequest request,@PathVariable("ratio") Float ratio, HttpServletResponse response) throws IOException {
        stockDataService.processData(new KdjWeekRatioStrategyLiangBiMianInOut(ratio));
        return "正在分析，请耐心等待";
    }
    /**
     * 100天内kdj黄金叉大于10
     **/
    @ResponseBody
    @RequestMapping("/start/kdj_count")
    public String queryStockDataByKdjWeekMain() throws IOException {
        stockDataService.processData(new GodenKdjCountStrategy(),100);
        return "正在分析，请耐心等待";
    }

    /**
     * kdj日周月上翘
     **/
    @ResponseBody
    @RequestMapping("/start/day_week_month_up")
    public String dayWeekMonthUp() throws IOException {
        stockDataService.processData(new KdjDayWeekMonthXStrategy());
        return "正在分析，请耐心等待";
    }
}