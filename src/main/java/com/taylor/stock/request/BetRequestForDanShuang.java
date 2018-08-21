package com.taylor.stock.request;

import com.taylor.common.JsonUtil;
import com.taylor.yicai.entity.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.taylor.common.Constants.*;

/**
 * @author taylor
 */
public class BetRequestForDanShuang {
    public static synchronized String postOrder(String gameId, String periodId, List<Order> orderList) {
        PostMethod method = new PostMethod(BASE_URL + "/OfficialAddOrders/AddOrders");
        StringBuilder stringBuffer = null;
        try {
            HttpClient client = new HttpClient();
            NameValuePair[] data = {new NameValuePair("gameId", "123"), new NameValuePair("periodId", periodId), new NameValuePair("isSingle", "false"), new NameValuePair("canAdvance", "false"), new NameValuePair("orderList", JsonUtil.transfer2JsonString(orderList))};

            method.setRequestBody(data);
            method.setRequestHeader("Referer", BASE_URL + "/OffcialOtherGame/Index/26");
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
            System.out.println("下单出错了" + e.getMessage());
            return "下单出错了";
        }
        if (stringBuffer != null) {
            return stringBuffer.toString();
        }
        return "";

    }

    public static void bet(int times) throws InterruptedException {
        Order order = new Order(BetGameEnum.getRandomBetGame(), BetStrategyEnum.getRandomBetStrategy(), times, BillEnum.FENG);
        List<Order> list = new ArrayList<>();
        list.add(order);
        String result = postOrder("123", NewPeriodDataRequest.queryLatestDataPeriod("123").getFid(), list);
        while (!result.contains("投注成功")) {
            System.out.println("未投注成功重试");
            result = postOrder("123", NewPeriodDataRequest.queryLatestDataPeriod("123").getFid(), list);
            Thread.sleep(15000);
        }
        Account account = AccountRequest.getAccount();
        System.out.println("账户：" + account.getAccountName() + " 投注金额：" + order.getA() + "元,倍数：" + times + " 余额：" + account.getCreditBalance() + "元" + " 失败次数：" + FAIL_TIME + " 重试次数: " + REPEAT_TIME + " 重试失败次数：" + REPEAT_FAILT_TIME + " 重试成功次数：" + REPEAT_SUCCESS_TIME);
        MyOrder myOrder = MyOrderListRequest.postOrder("123", 1).get(0);
        if (result.contains(myOrder.getOrderId())) {
            while (myOrder.getPeriodStatus() != 4) {
                Thread.sleep(20000);
                myOrder = MyOrderListRequest.postOrder("123", 1).get(0);
            }
            if (myOrder.getOrderResult() == 2) {
                System.out.println("中奖:" + myOrder.getBettingBalance() + "元");
                if (REPEAT_TIME == 0) {
                    FAIL_TIME = 0;
                    bet(initTime);
                } else {
                    REPEAT_SUCCESS_TIME++;
                    /**只有成功次数是失败次数多一次才能回本且有赚头**/
                    if (REPEAT_SUCCESS_TIME > REPEAT_FAILT_TIME) {
                        REPEAT_TIME = 0;
                        REPEAT_FAILT_TIME = 0;
                        REPEAT_SUCCESS_TIME = 0;
                        FAIL_TIME = 0;
                        bet(initTime);
                    } else {
                        /**重试虽然成功，但未回本，继续**/
                        REPEAT_TIME++;
                        bet(times);
                    }
                }
            } else {
                if (REPEAT_TIME == 0) {
                    FAIL_TIME++;
                }
                /**未到重试次数**/
                if (REPEAT_TIME == 0 && FAIL_TIME < FAIL_LIMIT) {
                    times = (times << 1) + times / 2;
                } else {
                    /**第一次重试当然不算是重试失败了**/
                    if (REPEAT_TIME > 0) {
                        REPEAT_FAILT_TIME++;
                    }
                    /**重试的时候双倍，这时成功数只要大于失败数一次就够了**/
                    if (REPEAT_TIME == 0) {
                        times = (times << 1) + times / 2;
                    }
                    /**重试的时候是有损失的，失败时添加缺失因子,弥补缺失**/
                    times = times + 1;
                    /**到重复次数了**/
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