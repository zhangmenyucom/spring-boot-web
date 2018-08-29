package com.taylor.stock.request;

import com.taylor.yicai.entity.Account;
import com.taylor.yicai.entity.MyOrder;
import com.taylor.yicai.entity.Order;

import java.util.ArrayList;
import java.util.List;

import static com.taylor.common.Constants.*;

/**
 * @author taylor
 */
public class BetRequestForDanShuang {

    public static void bet(int times) throws InterruptedException {
        Thread.sleep(10000);
        Order order = Order.getDanShuangOrder(times,BILLUNIT);
        List<Order> list = new ArrayList<>();
        list.add(order);
        String result = ApiClient.postOrder(GAMEID, list);
        while (!result.contains("投注成功")) {
            result = ApiClient.postOrder(GAMEID, list);
            Thread.sleep(12000);
        }
        Account account = ApiClient.getAccount();
        System.out.println("账户：" + account.getAccountName() + " 投注金额：" + order.getA() + "元,倍数：" + times + " 余额：" + account.getCreditBalance() + "元" + " 失败次数：" + FAIL_TIME + " 重试次数: " + REPEAT_TIME + " 重试失败次数：" + REPEAT_FAILT_TIME + " 重试成功次数：" + REPEAT_SUCCESS_TIME);
        MyOrder myOrder = ApiClient.getMyOrderList(GAMEID,1).get(0);
        if (result.contains(myOrder.getOrderId())) {
            while (myOrder.getPeriodStatus() != 4) {
                Thread.sleep(20000);
                myOrder = ApiClient.getMyOrderList(GAMEID,1).get(0);
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
                        /**到达上限时停止下注5分钟，以缓解必要压力**/
                        Thread.sleep(5*60*1000);
                        times = (times << 1) + times / 2;
                    }
                    /**重试的时候是有损失的，失败时添加缺失因子,弥补缺失**/
                    times = times + FACTOR;
                    /**到重复次数了**/
                    REPEAT_TIME++;

                    if (REPEAT_FAILT_TIME - REPEAT_SUCCESS_TIME > 10) {
                        /**10次以上是扳不回来的了，即使板回来成本太大**/
                        REPEAT_TIME = 0;
                        REPEAT_FAILT_TIME = 0;
                        REPEAT_SUCCESS_TIME = 0;
                        FAIL_TIME = 0;
                    }
                }
                bet(times);
            }
        }
    }

    public static void main(String... args) throws InterruptedException {
        bet(initTime);
    }
}