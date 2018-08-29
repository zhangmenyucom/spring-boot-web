package com.taylor.api;/**
 * ${author} on 2018/8/29.
 */

import com.taylor.entity.stock.TencentTodayBaseInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/29 8:49
 */
public interface Api {


    @GET
    Call<TencentTodayBaseInfo> getStackBaseInfo(@Query("q") String q);
}
