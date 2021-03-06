package com.taylor.controller;

import com.taylor.entity.TestEntity;
import com.taylor.service.RedisService;
import com.taylor.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/test")
@Controller
@Slf4j
public class TestController extends BaseAction {

    @Autowired
    private TestService testService;

    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping("/query")
    public List<TestEntity> queryTest(TestEntity test, HttpServletRequest request, HttpServletResponse response) {
        log.debug("这只是一个测试");
        return testService.find(test);
    }

    @GetMapping("get")
    @ResponseBody
    public String getRedis() {

        return (String) redisService.get("name");
    }
}
