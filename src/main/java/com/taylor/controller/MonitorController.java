package com.taylor.controller;

import com.taylor.stock.request.KdjMonitor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 测试
 *
 * @author Taylor
 * @version v.0.1
 */
@RestController
@RequestMapping("/monitor/")
public class MonitorController {
    private static volatile int a = 0;

    @RequestMapping("/start")
    public String startMonitor() {
        KdjMonitor.a = 0;
        KdjMonitor kdjMonitor1 = new KdjMonitor("sh510900");
        KdjMonitor kdjMonitor2 = new KdjMonitor("sh510710");
        kdjMonitor1.start();
        kdjMonitor2.start();
        return "正在监控";
    }

    @RequestMapping("/stop")
    public String helloJsp(Map<String, Object> map) {
        KdjMonitor.a = 1;
        return "已停止";
    }
}