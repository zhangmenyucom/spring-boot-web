package com.taylor.api;

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import com.taylor.common.JsonUtil;
import com.taylor.common.KLineTypeEnum;
import com.taylor.common.Retrofits;
import com.taylor.common.StringConverterFactory;
import com.taylor.entity.StockBaseInfo;
import com.taylor.entity.StockBusinessinfo;
import com.taylor.entity.TongHuaShunStockBase;
import com.taylor.entity.stock.*;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.taylor.common.Constants.*;


/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/29 8:52
 */
public class ApiClient {

    public static final Retrofit retrofitTencent = new Retrofit.Builder().addConverterFactory(StringConverterFactory.create())
            .baseUrl(TENCENT_PREFIX).build();

    public static final Retrofit retrofitTongHuaShun = new Retrofit.Builder().addConverterFactory(StringConverterFactory.create())
            .baseUrl(TONGHUASHUN_PREFIX).build();

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

    public static Api apiTongHuaShun = retrofitTongHuaShun.create(Api.class);


    /**
     * 获取盘口数据
     **/
    public static StockPanKouData getPanKouData(String q) {
        String result = null;
        try {
            result = Retrofits.execute(apiTencent.getStackBaseInfo(q.toLowerCase()));
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
    public static List<TencentDayData> getTencentKlineInfo(String stockCode, int count) {
        try {
            String apiresult = Retrofits.execute(apiTencentKline.getTencentKlineInfo(stockCode));
            String[] result = apiresult.replace("\\", "").split("n");
            List<TencentDayData> list = new ArrayList<>(1);
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
     * 同花顺数据
     **/
    public static TongHuaShunStockBase getTongHuaShenBaseInfo(String stockCode) {
        try {
            String result = Retrofits.execute(apiTongHuaShun.getTongHuaShenBaseInfo("real", "stock", "json", "showStockData", stockCode.substring(2).toLowerCase()));

            Map<String, Object> stockMap = JsonUtil.readJson2Map(result.substring(result.indexOf("(") + 1, result.indexOf(")")));
            Map<String, Object> info = (Map<String, Object>) stockMap.get("info");
            Map<String, Object> nameMap = (Map<String, Object>) info.get(stockCode.substring(2));
            Map<String, Object> data = (Map<String, Object>) stockMap.get("data");
            Map<String, Object> dataStockMap = (Map<String, Object>) data.get(stockCode.substring(2));
            TongHuaShunStockBase tongHuaShunStockBase = new TongHuaShunStockBase();
            tongHuaShunStockBase.setStockName(nameMap.get("name").toString());
            tongHuaShunStockBase.setClose(Double.valueOf(dataStockMap.get("10").toString()));
            tongHuaShunStockBase.setPreClose(Double.valueOf(dataStockMap.get("6").toString()));
            tongHuaShunStockBase.setAmplitudeRatio(Double.valueOf(dataStockMap.get("526792").toString()));
            tongHuaShunStockBase.setCapitalization(Double.valueOf(dataStockMap.get("3475914").toString()).longValue());
            tongHuaShunStockBase.setExchange(stockCode.substring(0, 2));
            tongHuaShunStockBase.setHigh(Double.valueOf(dataStockMap.get("8").toString()));
            tongHuaShunStockBase.setLow(Double.valueOf(dataStockMap.get("9").toString()));
            tongHuaShunStockBase.setNetChange(Double.valueOf(dataStockMap.get("264648").toString()));
            tongHuaShunStockBase.setNetChangeRatio(Double.valueOf(dataStockMap.get("199112").toString()));
            tongHuaShunStockBase.setOpen(Double.valueOf(dataStockMap.get("7").toString()));
            tongHuaShunStockBase.setTurnoverRatio(Double.valueOf(dataStockMap.get("1968584").toString()));
            tongHuaShunStockBase.setVolume(Double.valueOf(dataStockMap.get("19").toString()).longValue());
            tongHuaShunStockBase.setLiangBi(Double.valueOf(dataStockMap.get("19").toString()));
            tongHuaShunStockBase.setToltalHands(Double.valueOf(dataStockMap.get("13").toString()).longValue() / 100);
            tongHuaShunStockBase.setStockCode(stockCode);
            return tongHuaShunStockBase;
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
        for (int i = 0; i < 1000; i++) {
            System.out.println(getTongHuaShenBaseInfo("sz000430"));
        }
    }


}
