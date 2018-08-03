package com.taylor.controller;

import com.taylor.entity.TestEntity;
import com.taylor.service.RedisService;
import com.taylor.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Taylor
 */
@Api(tags = "swagger测试",description = "swagger测试")
@RestController
@RequestMapping("/api/swagger")
@Slf4j
public class SwaggerController extends BaseAction {

    @Autowired
    private TestService testService;

    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "获取helloWorld", notes = "简单SpringMVC请求")
    @PostMapping("/query")
    public List<TestEntity> queryTest(@ApiParam @Valid TestEntity test, HttpServletRequest request, HttpServletResponse response) {
        return testService.find(test);
    }
}
