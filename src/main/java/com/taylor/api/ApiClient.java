package com.taylor.api;

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import com.taylor.common.Retrofits;
import com.taylor.common.StringConverterFactory;
import com.taylor.entity.StockBaseInfo;
import com.taylor.entity.stock.*;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;

import static com.taylor.common.Constants.*;


/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/29 8:52
 */
public class ApiClient {

    public static final Retrofit retrofitTencent = new Retrofit.Builder().addConverterFactory(StringConverterFactory.create())
            .baseUrl(TENCENT_PREFIX).build();

    public static final Retrofit retrofitBaidu = new Retrofit.Builder().addConverterFactory(new Retrofit2ConverterFactory())
            .baseUrl(BAIDU_PREFIX).build();

    public static final Retrofit retrofitSina = new Retrofit.Builder().addConverterFactory(new Retrofit2ConverterFactory())
            .baseUrl(SINA_PREFIX).build();

    public static Api apiTencent = retrofitTencent.create(Api.class);

    public static Api apiBaidu = retrofitBaidu.create(Api.class);

    public static Api apiSina = retrofitSina.create(Api.class);


    /**
     * 获取盘口数据
     **/
    public static StockPanKouData getPanKouData(String q) {
        String result = null;
        try {
            result = Retrofits.execute(apiTencent.getStackBaseInfo(q));
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = result.substring(result.indexOf("=") + 2, result.indexOf(";") - 1);
        String[] datas = result.split("~");
        StockPanKouData stockPanKouData = new StockPanKouData();
        stockPanKouData.setStockCode(datas[2]);
        stockPanKouData.setStockName(datas[1]);
        stockPanKouData.setCurrentPrice(Double.valueOf(datas[3]));
        stockPanKouData.setYesPrice(Double.valueOf(datas[4]));
        stockPanKouData.setOpenPrice(Double.valueOf(datas[5]));
        stockPanKouData.setExchangeTotal(Double.valueOf(datas[6]));
        stockPanKouData.setUpDownMount(Double.valueOf(datas[31]));
        stockPanKouData.setUpDownMountPercent(Double.valueOf(datas[32]));

        stockPanKouData.setLiangBi(Double.valueOf(datas[49]));
        stockPanKouData.setAmplitude(datas[43] + "%");
        stockPanKouData.setBowtomPrice(Double.valueOf(datas[48]));
        stockPanKouData.setTopPrice(Double.valueOf(datas[47]));
        stockPanKouData.setMaxPrice(Double.valueOf(datas[33]));
        stockPanKouData.setMiniPrice(Double.valueOf(datas[34]));
        stockPanKouData.setExchangeValue(Double.valueOf(datas[37]));
        stockPanKouData.setExchangeRatio(Double.valueOf(datas[38]));
        stockPanKouData.setMarketEarnActive(Double.valueOf(datas[52]));
        stockPanKouData.setMarketEarnStatic(Double.valueOf(datas[53]));
        stockPanKouData.setOuter(Double.valueOf(datas[7]));
        stockPanKouData.setInner(Double.valueOf(datas[8]));
        stockPanKouData.setTotalValue(Double.valueOf(datas[45]));
        stockPanKouData.setMarketValue(Double.valueOf(datas[44]));
        return stockPanKouData;
    }


    /**
     * 最近日K
     **/
    public static List<MashData> getLatestResult(int count, String stockCode) {
        try {
            MashDataResponse execute = Retrofits.execute(apiBaidu.getLatestResult(1, count, "no", "pc", 1, "xxx", "100", stockCode, "json"));
            return execute.getMashData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

    /**
     * 最近日K
     **/
    public static StockBaseInfo getBaseStockInfo(String stockCode) {
        try {
            StockBaseInfoResponse execute = Retrofits.execute(apiBaidu.getBaseStockInfo("no", "pc", 1, "xxx", "100", stockCode, "json"));
            return execute.getData().get(0).setStockCode(stockCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 分时数据
     **/
    public static TimeStockDataResponse getTimeDataInfo(String stockCode) {
        try {
            return Retrofits.execute(apiBaidu.getTimeDataInfo("no", "pc", 1, "xxx", "100", stockCode, "json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 新浪历史数据
     **/
    public static List<HistoryData> getHistoryData(String symbol, int datalen) {
        try {
            return Retrofits.execute(apiSina.getHistoryData(symbol, 240, datalen));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String... args) {
        System.out.println(getTimeDataInfo("sz000430"));
    }


}
