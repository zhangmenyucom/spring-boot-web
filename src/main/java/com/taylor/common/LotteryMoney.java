package com.taylor.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author weilin.wang
 */
public class LotteryMoney {
    private static final String url = "https://chart.cp.360.cn/zst/qkj/?lotId=%s&issue=%s";
    private static final int lotId = 255401;
    private static int issue = 180823044;
    private static String forecastNumber = randomNumber();
    private static FileWriter fw;

    static {
        try {
            fw = new FileWriter("d:/caipiao.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String... args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Make(), 0, 10, TimeUnit.MILLISECONDS);
    }

    private static class Make implements Runnable {
        private Integer i = 0;

        @Override
        public void run() {
            String winNumber = winNumber();
            boolean isWin = win(forecastNumber, winNumber);
            String s = String.format("=====第%s期预测号码:%s,开奖号码:%s,%s \n", issue, forecastNumber, winNumber, isWin);
            try {
                fw.write(s);
                fw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (isWin) {
            } else {
                i++;
            }
            if (isWin || i > 1) {
                forecastNumber = randomNumber();
                i = 0;
            }
            issue++;
        }
    }

    private static boolean win(String forecastNumber, String winNumber) {
        if (forecastNumber.contains(String.valueOf(winNumber.charAt(2)))
                && forecastNumber.contains(String.valueOf(winNumber.charAt(3)))
                && forecastNumber.contains(String.valueOf(winNumber.charAt(4)))
                && isGroupSix(winNumber)) {
            return true;
        }
        return false;
    }

    private static boolean isGroupSix(String winNumber) {
        if (winNumber.charAt(2) == winNumber.charAt(3) || winNumber.charAt(2) == winNumber.charAt(4) || winNumber.charAt(3) == winNumber.charAt(4)) {
            return false;
        }
        return true;
    }

    private static String randomNumber() {
        Random random = new SecureRandom();
        String s = "0123456789";
        do {
            int n1 = random.nextInt(9);
            s = s.replace(String.valueOf(n1), "");
        } while (s.length() > 8);
        return s;
    }

    private static String winNumber() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> json = new HashMap<>();
        json.put("lotId", lotId);
        json.put("issue", issue);
        ResponseEntity<JSONObject> res = restTemplate.getForEntity(String.format(url, lotId, issue), JSONObject.class);
        return res.getBody().getObject("0", LinkedHashMap.class).get("WinNumber").toString();
    }
}
