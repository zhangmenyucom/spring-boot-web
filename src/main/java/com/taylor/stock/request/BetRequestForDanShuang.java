package com.taylor.stock.request;

import com.taylor.common.JsonUtil;
import com.taylor.common.MailUtils;
import com.taylor.yicai.entity.BetStrategyEnum;
import com.taylor.yicai.entity.BillEnum;
import com.taylor.yicai.entity.MyOrder;
import com.taylor.yicai.entity.Order;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static com.taylor.common.Constants.COOKIE;
import static com.taylor.common.Constants.initTime;

/**
 * @author taylor
 */
public class BetRequestForDanShuang {
    public static synchronized String postOrder(String gameId, String periodId, List<Order> orderList) {
        PostMethod method = new PostMethod("https://www.yc2025.com/OfficialAddOrders/AddOrders");
        StringBuilder stringBuffer = null;
        try {
            HttpClient client = new HttpClient();
            NameValuePair[] data = {new NameValuePair("gameId", "123"), new NameValuePair("periodId", periodId), new NameValuePair("isSingle", "false"), new NameValuePair("canAdvance", "false"), new NameValuePair("orderList", JsonUtil.transfer2JsonString(orderList))};

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

    public static void bet(int times, List<BetStrategyEnum> strategyEnumList) throws InterruptedException {
        SecureRandom secureRandom = new SecureRandom();
        BetStrategyEnum betStrategyEnum = strategyEnumList.get(secureRandom.nextInt(10000) % (strategyEnumList.size() - 1));
        Order order = new Order(21024, betStrategyEnum, times, BillEnum.LI);
        System.out.println("当前投注金额为：" + order.getA() + "元,当前倍数为：" + times);
        List<Order> list = new ArrayList<>();
        list.add(order);
        String result = postOrder("123", NewPeriodDataRequest.queryLatestDataPeriod("123").getFid(), list);
        while (!result.contains("投注成功")) {
            if (result.contains("传入的订单列表，格式不正确")) {
                MailUtils.sendMail("格式不正确", result);
            }
            System.out.println("未投注成功重试");
            result = postOrder("123", NewPeriodDataRequest.queryLatestDataPeriod("123").getFid(), list);
            Thread.sleep(10000);
        }
        MyOrder myOrder = MyOrderListRequest.postOrder("123", 1).get(0);
        if (result.contains(myOrder.getOrderId())) {
            while (myOrder.getPeriodStatus() != 4) {
                Thread.sleep(20000);
                myOrder = MyOrderListRequest.postOrder("123", 1).get(0);
            }
            if (myOrder.getOrderResult() == 2) {
                System.out.println("恭喜你中奖:" + myOrder.getBettingBalance() + "元");
                times = initTime;
                bet(times, strategyEnumList);
            } else {
                times = (times << 1) + ((times >> 1) > 2 ? times >> 1 : 2);
                System.out.println("未中奖");
                bet(times, strategyEnumList);
            }
        }
    }

    public static void main(String... args) throws InterruptedException {
        List<BetStrategyEnum> strategyEnumList = new ArrayList<>();
        strategyEnumList.add(BetStrategyEnum.D_DS);
        strategyEnumList.add(BetStrategyEnum.S_DS);
        strategyEnumList.add(BetStrategyEnum.DS_S);
        strategyEnumList.add(BetStrategyEnum.DS_D);
        bet(initTime, strategyEnumList);
    }
}