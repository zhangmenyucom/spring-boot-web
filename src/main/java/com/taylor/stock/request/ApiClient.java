package com.taylor.stock.request;/**
 * ${author} on 2018/8/28.
 */

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.taylor.common.JsonUtil;
import com.taylor.common.Retrofits;
import com.taylor.yicai.entity.*;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;

import static com.taylor.common.Constants.BASE_URL;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/28 14:54
 */
public class ApiClient {

    public static final Retrofit retrofit = new Retrofit.Builder().addConverterFactory(new Retrofit2ConverterFactory()).baseUrl(BASE_URL).build();
    public static Api api = retrofit.create(Api.class);

    /**
     * 获取最新proidId
     **/
    public static NewPeriodEntity getNewPeriodEntity(String gameId) {
        try {
            return Retrofits.execute(api.getPeriod(gameId));
        } catch (IOException e) {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println(e.getMessage());
            return getNewPeriodEntity(gameId);
        }
    }

    /**
     * 获取历史订单
     **/
    public static List<MyOrder> getMyOrderList(String gameId, int count) {
        String body;
        try {
            body = Retrofits.execute(api.getMyOrder(gameId, count));
        } catch (IOException e) {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println(e.getMessage());
            return getMyOrderList(gameId, count);
        }
        return new Gson().fromJson(body.replace("\\", ""), new TypeToken<List<MyOrder>>() {
        }.getType());
    }

    /**
     * 获取账号信息
     **/
    public static Account getAccount() {
        try {
            return Retrofits.execute(api.getMyAccount());
        } catch (IOException e) {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println(e.getMessage());
            return getAccount();
        }
    }

    /**
     * 下注
     **/
    public static String postOrder(String gameId, List<Order> orderList) {
        try {
            return Retrofits.execute(api.postOrder(gameId, getNewPeriodEntity(gameId).getFid(), false, false, JsonUtil.transfer2JsonString(orderList)));
        } catch (IOException e) {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println(e.getMessage());
            return postOrder(gameId, orderList);
        }
    }

    /**
     * 历史订单数据
     **/
    public static List<PeriodResult> getHistoryData(String gameId, int count) {
        try {
            return api.getHistoryData(gameId, count, 1).execute().body().getList();
        } catch (IOException e) {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println(e.getMessage());
            return getHistoryData(gameId, count);
        }
    }

    public static void main(String... args) {
        System.out.println(JsonUtil.transfer2JsonString(getHistoryData("123", 100)));
    }
}
