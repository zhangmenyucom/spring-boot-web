package com.taylor.controller;

import com.taylor.api.ApiClient;
import com.taylor.common.StockUtils;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockData;
import com.taylor.entity.StockOnShelf;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.service.RecmdStockService;
import com.taylor.service.StockDataService;
import com.taylor.service.StockOnShelfService;
import com.taylor.stock.common.StrategyEnum;
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
import java.util.stream.Collectors;

import static com.taylor.common.ConstantsInits.FOUND_VIEW;

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
        for (int i = 12; i >= 0; i--) {
            Calendar cl = Calendar.getInstance();
            cl.setTime(StockUtils.getDateAfter(now, -i));
            if (isWeekend(cl)) {
                continue;
            }
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
            onshelfMap.put(stockOnShelf.getStockCode().toLowerCase(), stockOnShelf);
        }
        if (FOUND_VIEW == 1) {
            for (RecmdStock stock : recmdStocks) {
                stock.setFoundInOutEntity(ApiClient.getTongHuashunFoundInOut(stock.getStockCode()));
            }
        }
        map.put("recordTime", sdf.format(recmdStock.getRecordTime()));
        map.put("type", type);
        map.put("found_view", FOUND_VIEW);
        map.put("recmdList", recmdStocks);
        map.put("strategyName", StrategyEnum.getEnumValue(type));
        map.put("listDate", listDate);
        map.put("onshelfMap", onshelfMap);
        map.put("bigDataList", ApiClient.getBigDataList());
        return "/recmd";
    }

    @RequestMapping("/industry/list")
    public String industryList(Map<String, Object> map) {
        List<String> industryList = stockDataService.getIndustryList();
        map.put("industryList", industryList);
        return "/industrys";
    }

    @RequestMapping("/reachcost")
    public String reachcost(Map<String, Object> map) {
        List<RecmdStock> recmdStocks = recmdStockService.reachcost();
        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(new StockOnShelf());
        Map<String, Object> onshelfMap = new HashMap<>();
        for (StockOnShelf stockOnShelf : stockOnShelves) {
            onshelfMap.put(stockOnShelf.getStockCode().toLowerCase(), stockOnShelf);
        }
        if (FOUND_VIEW == 1) {
            for (RecmdStock stock : recmdStocks) {
                stock.setFoundInOutEntity(ApiClient.getTongHuashunFoundInOut(stock.getStockCode()));
            }
        }
        map.put("found_view", FOUND_VIEW);
        map.put("recmdList", recmdStocks);
        map.put("onshelfMap", onshelfMap);
        return "/reachcost";
    }

    @RequestMapping("/shelf")
    public String shlef(Map<String, Object> map) {
        StockOnShelf stockOnShelfQuery = new StockOnShelf();
        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(stockOnShelfQuery);
        if (FOUND_VIEW == 1) {
            for (StockOnShelf stockOnShelf : stockOnShelves) {
                stockOnShelf.setFoundInOutEntity(ApiClient.getTongHuashunFoundInOut(stockOnShelf.getStockCode()));
            }
        }
        map.put("stockOnShelves", stockOnShelves);
        map.put("found_view", FOUND_VIEW);
        map.put("bigDataList", ApiClient.getBigDataList());
        return "/shelf";
    }

    @RequestMapping("/related/{code}")
    public String related(@PathVariable("code") String code, Map<String, Object> map, @RequestParam(defaultValue = "", name = "recordTime") String recordTime, HttpServletRequest request) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        HttpSession session = request.getSession();
        List<String> listDate = new ArrayList<>();
        Date now = new Date();
        for (int i = 12; i >= 0; i--) {
            Calendar cl = Calendar.getInstance();
            cl.setTime(StockUtils.getDateAfter(now, -i));
            if (isWeekend(cl)) {
                continue;
            }
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

        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(new StockOnShelf());
        Map<String, Object> onshelfMap = new HashMap<>();
        for (StockOnShelf stockOnShelf : stockOnShelves) {
            onshelfMap.put(stockOnShelf.getStockCode().toLowerCase().toLowerCase(), stockOnShelf);
        }

        List<StockData> stockDataList = stockDataService.findDataByCodeType(code);
        List<RecmdStock> recmdStocksList = stockDataList.stream().map(this::getRecmdStock).collect(Collectors.toList());
        onshelfMap.put(code, "");
        Collections.sort(recmdStocksList, (o1, o2) -> o1.getChangeRatioToday() < o2.getChangeRatioToday() ? 1 : -1);
        if (FOUND_VIEW == 1) {
            for (RecmdStock stock : recmdStocksList) {
                stock.setFoundInOutEntity(ApiClient.getTongHuashunFoundInOut(stock.getStockCode()));
            }
        }
        map.put("recordTime", sdf.format(recmdStock.getRecordTime()));
        map.put("type", 1);
        map.put("found_view", FOUND_VIEW);
        map.put("recmdList", recmdStocksList);
        map.put("strategyName", "同行比较");
        map.put("listDate", listDate);
        map.put("onshelfMap", onshelfMap);
        map.put("bigDataList", ApiClient.getBigDataList());
        return "/compare";
    }

    @RequestMapping("/related/name/{name}")
    public String related(@PathVariable("name") String name, Map<String, Object> map) throws ParseException {

        List<StockOnShelf> stockOnShelves = stockOnShelfService.find(new StockOnShelf());
        Map<String, Object> onshelfMap = new HashMap<>();
        for (StockOnShelf stockOnShelf : stockOnShelves) {
            onshelfMap.put(stockOnShelf.getStockCode().toLowerCase().toLowerCase(), stockOnShelf);
        }

        List<StockData> stockDataList = stockDataService.findDataByIndustryName(name);
        List<RecmdStock> recmdStocksList = stockDataList.stream().map(this::getRecmdStock).collect(Collectors.toList());
        Collections.sort(recmdStocksList, (o1, o2) -> o1.getChangeRatioToday() < o2.getChangeRatioToday() ? 1 : -1);
        if (FOUND_VIEW == 1) {
            for (RecmdStock stock : recmdStocksList) {
                stock.setFoundInOutEntity(ApiClient.getTongHuashunFoundInOut(stock.getStockCode()));
            }
        }
        map.put("recmdList", recmdStocksList);
        map.put("strategyName", "同行比较");
        map.put("onshelfMap", onshelfMap);
        map.put("found_view", FOUND_VIEW);
        return "/compare";
    }

    private RecmdStock getRecmdStock(StockData stockData) {
        StockPanKouData panKouData = ApiClient.getPanKouData(stockData.getStockCode());
        RecmdStock recmdStockTemp = new RecmdStock();
        recmdStockTemp.setStockCode(stockData.getStockCode())
                .setAuthorOpinion("-")
                .setChangeRatioToday(panKouData.getUpDownMountPercent())
                .setCurrentPrice(panKouData.getCurrentPrice())
                .setIndustry(stockData.getIndustry())
                .setRecordPrice(panKouData.getCurrentPrice())
                .setTurnoverRatio(panKouData.getExchangeRatio())
                .setStockName(panKouData.getStockName())
                .setLiangbi(panKouData.getLiangBi())
                .setLiangbiToday(panKouData.getLiangBi())
                .setId(stockData.getId());
        return recmdStockTemp;
    }

    private boolean isWeekend(Calendar cal) {
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 6 || week == 0) {//0代表周日，6代表周六
            return true;
        }
        return false;
    }
}
