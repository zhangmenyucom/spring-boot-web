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
    @RequestMapping("/hello")
    public String helloJsp(Map<String, Object> map) {
        System.out.println("HelloController.helloJsp().hello=1231");
        map.put("hello", "hello world!");
        return "/hello";
    }
}