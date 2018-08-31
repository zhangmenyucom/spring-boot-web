package com.taylor.api;/**
 * ${author} on 2018/8/29.
 */

import com.taylor.entity.StockBusinessinfo;
import com.taylor.entity.stock.HistoryData;
import com.taylor.entity.stock.MashDataResponse;
import com.taylor.entity.stock.StockBaseInfoResponse;
import com.taylor.entity.stock.TimeStockDataResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/29 8:49
 */
public interface Api {
    /**
     * 腾讯盘口数据
     **/
    @GET("/")
    Call<String> getStackBaseInfo(@Query("q") String q);

    /**
     * 腾讯资金流向
     **/
    @GET("/")
    Call<String> getStockFundInOutData(@Query("q") String q);

    /**
     * 腾讯日K
     **/
    @GET("/flashdata/hushen/latest/daily/{stockCode}.js")
    Call<String> getTencentKlineInfo(@Path("stockCode") String stockCode);

    /**
     * 百度日K数据
     **/
    @GET("/api/stocks/stockdaybar")
    Call<MashDataResponse> getLatestResult(@Query("step") int step,
                                           @Query("count") int count,
                                           @Query("fq_type") String fq_type,
                                           @Query("from") String from,
                                           @Query("os_ver") int os_ver,
                                           @Query("cuid") String cuid,
                                           @Query("vv") String vv,
                                           @Query("stock_code") String stock_code,
                                           @Query("format") String format);

    /**
     * 百度股票数据
     **/
    @GET("/api/rails/stockbasicbatch")
    Call<StockBaseInfoResponse> getBaseStockInfo(@Query("fq_type") String fq_type,
                                                 @Query("from") String from,
                                                 @Query("os_ver") int os_ver,
                                                 @Query("cuid") String cuid,
                                                 @Query("vv") String vv,
                                                 @Query("stock_code") String stock_code,
                                                 @Query("format") String format);
    /**
     * 百度股票分时数据
     **/
    @GET("/api/stocks/stocktimeline")
    Call<TimeStockDataResponse> getTimeDataInfo(@Query("fq_type") String fq_type,
                                                 @Query("from") String from,
                                                 @Query("os_ver") int os_ver,
                                                 @Query("cuid") String cuid,
                                                 @Query("vv") String vv,
                                                 @Query("stock_code") String stock_code,
                                                 @Query("format") String format);


    /**
     * 百度股票基本面
     **/
    @GET("/api/stocks/stockbasicinfo")
    Call<StockBusinessinfo> queryStockBasicBussinessInfo(@Query("fq_type") String fq_type,
                                                         @Query("from") String from,
                                                         @Query("os_ver") int os_ver,
                                                         @Query("cuid") String cuid,
                                                         @Query("vv") String vv,
                                                         @Query("stock_code") String stock_code,
                                                         @Query("format") String format);

    /**
     * 新浪历史数据
     **/
    @GET("/quotes_service/api/json_v2.php/CN_MarketData.getKLineData")
    Call<List<HistoryData>> getHistoryData(@Query("symbol") String symbol, @Query("scale") int scale, @Query("datalen") int datalen);


    /**
     * 360kdj数据
     **/
    @Headers("Referer:https://gupiao.nicaifu.com")
    @POST("/Stock_router.php")
    Call<String> getKdjData(@Query("path") String path,
                                @Query("data[prod_code]") String code,
                                @Query("data[candle_period]") int candle,
                                @Query("data[candle_mode]")String candleMode,
                                @Query("data[data_count]")int count,
                                @Query("data[exFieldArr]")String field,
                                @Query("ts") long ts);


    /**
     * 新浪历史数据
     **/
    @GET("/quote.php")
    Call<String> getTongHuaShenBaseInfo(@Query("cate") String cate, @Query("type") String type, @Query("return") String ret,@Query("callback") String callback,@Query("code") String code);



}
