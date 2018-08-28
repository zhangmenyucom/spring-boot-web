package com.taylor.stock.request;

import com.taylor.yicai.entity.Account;
import com.taylor.yicai.entity.NewPeriodEntity;
import com.taylor.yicai.entity.PeriodResultResp;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.taylor.common.Constants.COOKIE;

public interface ApiRequest {
    /**
     * 获取下一期periodId
     **/
    @GET("/Shared/GetNewPeriod")
    Call<NewPeriodEntity> getPeriod(@Query("gameId") String gameId);

    /**
     * 获取我的订单
     **/
    @GET("/OffcialOtherGame/GetHistoryOrders")
    @Headers("Cookie:"+COOKIE)
    Call<String> getMyOrder(@Query("gameId")String gameId, @Query("num")int num);

    /**
     * 获取我的账户
     **/
    @GET("/AccountInfo/GetAccount")
    @Headers("Cookie:"+COOKIE)
    Call<Account> getMyAccount();


    /**
     * 下注
     **/
    @POST("/OfficialAddOrders/AddOrders")
    @Headers("Cookie:"+COOKIE)
    Call<String> postOrder(@Query("gameId")String gameId,@Query("periodId")String periodId,@Query("isSingle")boolean isSingle, @Query("canAdvance")boolean canAdvance, @Query("orderList")String orderList);

    /**
     * 获取历史数据
     **/
    @GET("/Result/GetLotteryResultList")
    Call<PeriodResultResp> getHistoryData(@Query("gameId")String gameId, @Query("pageSize")int  pageSize,@Query("pageIndex")int  pageIndex);


}