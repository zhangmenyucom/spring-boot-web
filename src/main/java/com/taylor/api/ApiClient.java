package com.taylor.api;/**
 * ${author} on 2018/8/29.
 */

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import com.taylor.entity.stock.TencentTodayBaseInfo;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.io.IOException;

import static com.taylor.common.Constants.TENCENT_PREFIX;


/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/29 8:52
 */
public class ApiClient {
    public static final Retrofit retrofit = new Retrofit.Builder().addConverterFactory(new Retrofit2ConverterFactory()).baseUrl(TENCENT_PREFIX).build();
    public static Api api = retrofit.create(Api.class);


    public static void main(String... args) throws IOException {
        System.out.println(getStackBaseInfo("sh603345"));
    }

    public static String  getStackBaseInfo(String q) throws IOException {
        return api.getStackBaseInfo(q).execute().body();
    }
}
