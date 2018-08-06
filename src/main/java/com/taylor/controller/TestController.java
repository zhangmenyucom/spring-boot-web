package com.taylor.controller;

import com.taylor.service.RedisService;
import com.taylor.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Taylor
 */
@RequestMapping("/test")
@Controller
@Slf4j
public class TestController extends BaseAction {

    @Autowired
    private TestService testService;

    @Autowired
    private RedisService redisService;


    @GetMapping("get")
    @ResponseBody
    public String getRedis() {
        return (String) redisService.get("name");
    }
}
