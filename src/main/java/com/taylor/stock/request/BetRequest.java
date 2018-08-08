package com.taylor.stock.request;

import com.taylor.common.JsonUtil;
import com.taylor.yicai.entity.Order;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author taylor
 */
public class BetRequest {
    private static PostMethod method = new PostMethod("https://www.yc2025.com/OfficialAddOrders/AddOrders");

    public static synchronized String postOrder(String gameId, String periodId, Order order) {
        StringBuilder stringBuffer = null;
        try {
            HttpClient client = new HttpClient();
            List<Order> orderList = new ArrayList<>();
            orderList.add(order);
            System.out.println(JsonUtil.transfer2JsonString(orderList));
            NameValuePair[] data = {new NameValuePair("gameId", gameId), new NameValuePair("periodId", periodId), new NameValuePair("isSingle", "false"), new NameValuePair("canAdvance", "false"), new NameValuePair("orderList", JsonUtil.transfer2JsonString(orderList))};
            method.setRequestBody(data);
            method.setRequestHeader("Referer", "https://www.yc2025.com/OffcialOtherGame/Index/26");
            method.setRequestHeader("Host", "www.yc2025.com");
            method.setRequestHeader("Origin", "https://www.yc2025.com");
            method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            method.setRequestHeader("Cookie", "ASP.NET_SessionId=di05huhgdzlchsgh1kmkrxbz; ValidateToken=bd5df4b0d5224c64d15f8ab2bde55665;SESSION_COOKIE=3; GeneralizToken=ec19f0526a45451c991831395f0ca35b; LoginSessionID=631b843bbca7af373c918574a8ca9875; multiSelect=false; GAMEID=26");
            //method.setContentChunked(true);
            // 提交表单
            client.executeMethod(method);
            // 字符流转字节流 循环输出，同get解释
            InputStream inputStream = method.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            stringBuffer = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();

    }

    public static void main(String... args) throws InterruptedException {
        for (int i = 0; i < 60; i++) {
            Order order = new Order();
            order.setA(0.004f).setC("单,双|双").setI("21023").setK(0).setM(4).setN(2).setT(1);
            System.out.println(postOrder("123", NewPeriodDataRequest.queryLatestDataPeriod("123").getFid(), order));
            Thread.sleep(60000);
        }
    }
}