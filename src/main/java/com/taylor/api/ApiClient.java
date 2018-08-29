package com.taylor.api;

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import com.taylor.common.KLineTypeEnum;
import com.taylor.common.Retrofits;
import com.taylor.common.StringConverterFactory;
import com.taylor.entity.StockBaseInfo;
import com.taylor.entity.StockBusinessinfo;
import com.taylor.entity.stock.*;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

    public static final Retrofit retrofitTencentKline = new Retrofit.Builder().addConverterFactory(StringConverterFactory.create())
            .baseUrl(TENCENT_KLINE_PREFIX).build();

    public static final Retrofit retrofit360 = new Retrofit.Builder().addConverterFactory(StringConverterFactory.create())
            .baseUrl(NICAIFU_PREFIX).build();

    public static final Retrofit retrofitBaidu = new Retrofit.Builder().addConverterFactory(new Retrofit2ConverterFactory())
            .baseUrl(BAIDU_PREFIX).build();

    public static final Retrofit retrofitSina = new Retrofit.Builder().addConverterFactory(new Retrofit2ConverterFactory())
            .baseUrl(SINA_PREFIX).build();

    public static Api apiTencent = retrofitTencent.create(Api.class);

    public static Api apiTencentKline = retrofitTencentKline.create(Api.class);

    public static Api apiBaidu = retrofitBaidu.create(Api.class);

    public static Api apiSina = retrofitSina.create(Api.class);

    public static Api api360 = retrofit360.create(Api.class);


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
     * 获取腾讯日K
     **/
    public static List<TencentDayData> getTencentKlineInfo(String stockCode,int count) {
        try {
            String apiresult = Retrofits.execute(apiTencentKline.getTencentKlineInfo(stockCode));
            String[] result = apiresult.replace("\\", "").split("n");
            List<TencentDayData> list=new ArrayList<>(1);
            for (int i = 0; i < count; i++) {
                TencentDayData tencentDayData = new TencentDayData();
                String[] data = result[result.length - 2 - i].trim().split(" ");
                tencentDayData.setOpen(Double.valueOf(data[1]));
                tencentDayData.setClose(Double.valueOf(data[2]));
                tencentDayData.setHigh(Double.valueOf(data[3]));
                tencentDayData.setLow(Double.valueOf(data[4]));
                tencentDayData.setTotalHands(Long.valueOf(data[5]));
                tencentDayData.setDate(data[0]);
                list.add(tencentDayData);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
     * 基本信息
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
     * 分时数据
     **/
    public static StockBusinessinfo queryStockBasicBussinessInfo(String stockCode) {
        try {
            return Retrofits.execute(apiBaidu.queryStockBasicBussinessInfo("no", "pc", 1, "xxx", "100", stockCode, "json"));
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

    /**
     * 360kdj
     **/
    public static String getKdjData(String stockCode, KLineTypeEnum kLineTypeEnum, int count) throws IOException {
        String code = stockCode.substring(2, stockCode.length()) + "." + stockCode.substring(0, 2).toUpperCase();
        return api360.getKdjData("/stock/stock/k_line", code, kLineTypeEnum.getKey(), "1", count, "kdj", new Date().getTime()).execute().body();
    }


    public static void main(String... args) {
        System.out.println(getTencentKlineInfo("sz000430",10));
    }


}
