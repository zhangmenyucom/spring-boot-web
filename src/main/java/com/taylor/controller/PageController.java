package com.taylor.controller;

import com.taylor.entity.StockUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
    public String index(Map<String, Object> map) {
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
        if(request.getSession().getAttribute("user")!=null) {
            StockUser stockUser = (StockUser) request.getSession().getAttribute("user");
            map.put("user",stockUser);
            return "/main";
        }
        return "/login";
    }

    @RequestMapping("/left")
    public String left(Map<String, Object> map) {
        return "/left";
    }

    @RequestMapping("/swich")
    public String swich(Map<String, Object> map) {
        return "/swich";
    }
}