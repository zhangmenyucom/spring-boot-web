package com.taylor.stock.request;

import tk.mybatis.mapper.util.StringUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author taylor
 */
public class GuZhenRequestTest {
    public static void  getGuZhengData(String stockCode) {
        try {
            StringBuilder buffer = new StringBuilder();

            String url = "http://d.10jqka.com.cn/v2/realhead/hs_600573/last.js";
            //发送get请求
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("host", "http://stockpage.10jqka.com.cn/");
            conn.setRequestProperty("Referer","http://stockpage.10jqka.com.cn/600573/");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
            conn.connect();
            //将返回的输入流转换成字符串
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            if (StringUtil.isEmpty(buffer.toString())) {
            }
            System.out.println(buffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) {
        for (int i = 100; i > 0; i--) {
            getGuZhengData("123");
        }
    }
}