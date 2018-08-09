package com.taylor.stock.request;

import com.taylor.common.JsonUtil;
import com.taylor.yicai.entity.BetStrategyEnum;
import com.taylor.yicai.entity.BillEnum;
import com.taylor.yicai.entity.Order;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.taylor.common.Constants.COOKIE;

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
            method.setRequestHeader("Cookie", COOKIE);
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

    public static void main(String... args) {
            Order order = new Order("21023", BetStrategyEnum.DS_S,1, BillEnum.LI);
            System.out.println(postOrder("123", NewPeriodDataRequest.queryLatestDataPeriod("123").getFid(), order));
    }
}