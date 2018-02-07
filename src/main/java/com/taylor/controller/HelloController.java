package com.taylor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 测试
 *
 * @author Taylor
 * @version v.0.1
 */
@Controller
@RequestMapping("/test/")
public class HelloController {

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
    public String main(Map<String, Object> map) {
        return "/main";
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