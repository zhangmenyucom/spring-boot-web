package com.taylor.controller;

import com.taylor.common.StockUtils;
import com.taylor.entity.StockUser;
import com.taylor.stock.common.StrategyEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 测试
 *
 * @author Taylor
 * @version v.0.1
 */
@Controller
@RequestMapping("/")
public class PageController {

    @RequestMapping("/index")
    public String index(Map<String, Object> map, HttpServletRequest request) {
       /* if (request.getSession().getAttribute("user") != null) {
            map.put("user", request.getSession().getAttribute("user"));
            return "/index";
        }*/
        return "/index";

    }

    @RequestMapping("/top")
    public String top(Map<String, Object> map) {
        return "/top";
    }

    @RequestMapping("/bottom")
    public String bottom(Map<String, Object> map) {
        return "/bottom";
    }

    @RequestMapping("/main")
    public String main(Map<String, Object> map, HttpServletRequest request) {
        StockUser stockUser=new StockUser();
        if (request.getSession().getAttribute("user") != null) {
             stockUser = (StockUser) request.getSession().getAttribute("user");
            map.put("user", stockUser);
            return "/main";
        }
         stockUser.setUserName("zhangmenyucom");
        map.put("user", stockUser);
        return "/main";
    }

    @RequestMapping("/left")
    public String left(Map<String, Object> map) {
        map.put("strategyEnumMap", StrategyEnum.map);
        return "/left";
    }

    @RequestMapping("/swich")
    public String swich(Map<String, Object> map) {
        return "/swich";
    }

    @RequestMapping("/news")
    public String news(Map<String, Object> map) {
        return "/news";
    }
}