package com.taylor.stock.request;

import com.taylor.common.JsonUtil;
import com.taylor.common.MailUtils;
import com.taylor.yicai.entity.MyOrder;
import com.taylor.yicai.entity.Order;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.taylor.common.Constants.*;
import static com.taylor.common.Constants.BASE_URL;
import static com.taylor.common.Constants.COOKIE;
import static com.taylor.common.Constants.initTime;

/**
 * @author taylor
 */
public class BetRequestForZuLiu {
    public static synchronized String postOrder(String gameId, String periodId, List<Order> orderList) {
        PostMethod method = new PostMethod(BASE_URL+"/OfficialAddOrders/AddOrders");
        StringBuilder stringBuffer = null;
        try {
            HttpClient client = new HttpClient();
            System.out.println(JsonUtil.transfer2JsonString(orderList));
            NameValuePair[] data = {new NameValuePair("gameId", "123"), new NameValuePair("periodId", periodId), new NameValuePair("isSingle", "false"), new NameValuePair("canAdvance", "false"), new NameValuePair("orderList", JsonUtil.transfer2JsonString(orderList))};

            System.out.println(JsonUtil.transfer2JsonString(data));
            method.setRequestBody(data);
            method.setRequestHeader("Referer", BASE_URL+"/OffcialOtherGame/Index/26");
            method.setRequestHeader("Host", BASE_URL);
            method.setRequestHeader("Origin", BASE_URL);
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

    public static void bet(int times) throws InterruptedException {
        Order order = new Order();
        order.setC(HistoryResultRequest.getNextContent("123", 6));
        order.setI(20979);
        order.setN(56);
        order.setT(1);
        order.setM(4);
        order.setK(0);
        order.setA(BigDecimal.valueOf(0.112));
        List<Order> list = new ArrayList<>();
        list.add(order);
        String result = postOrder("123", NewPeriodDataRequest.queryLatestDataPeriod("123").getFid(), list);
        System.out.println(result);
        while (!result.contains("投注成功")) {
            if (result.contains("传入的订单列表，格式不正确")) {
                MailUtils.sendMail("格式不正确", result);
            }
            System.out.println(JsonUtil.transfer2JsonString(list));
            result = postOrder("123", NewPeriodDataRequest.queryLatestDataPeriod("123").getFid(), list);
            System.out.println(result);
            Thread.sleep(10000);
        }
        MyOrder myOrder = MyOrderListRequest.postOrder("123", 1).get(0);
        if (result.contains(myOrder.getOrderId())) {
            System.out.println("请求最后订单状态:" + (myOrder.getPeriodStatus() == 4 ? "已开奖" : "未开奖"));
            while (myOrder.getPeriodStatus() != 4) {
                Thread.sleep(20000);
                myOrder = MyOrderListRequest.postOrder("123", 1).get(0);
                System.out.println("请求最后订单状态:" + (myOrder.getPeriodStatus() == 4 ? "已开奖" : "未开奖"));
            }
            if (myOrder.getOrderResult() == 2) {
                System.out.println("恭喜你中奖:" + myOrder.getBettingBalance() + "元");
                if (REPEAT_TIME == 0) {
                    FAIL_TIME = 0;
                    bet(initTime);
                } else {
                    REPEAT_TIME--;
                    bet(times);
                }
            } else {
                if (REPEAT_TIME == 0) {
                    FAIL_TIME++;
                }
                if (REPEAT_TIME == 0 && FAIL_TIME < FAIL_LIMIT) {
                    times = (times << 1) + 1;
                } else {
                    REPEAT_TIME++;
                }
                bet(times);
            }
        }
    }

    public static void main(String... args) throws InterruptedException {
        bet(initTime);
    }
}