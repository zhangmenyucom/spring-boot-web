package com.taylor.api;/**
 * ${author} on 2018/8/29.
 */

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import com.taylor.common.Retrofits;
import com.taylor.common.StringConverterFactory;
import com.taylor.entity.stock.StockPanKouData;
import retrofit2.Retrofit;

import java.io.IOException;

import static com.taylor.common.Constants.TENCENT_PREFIX;


/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/29 8:52
 */
public class ApiClient {

    public static final Retrofit retrofit = new Retrofit.Builder().addConverterFactory(StringConverterFactory.create())
            .addConverterFactory(new Retrofit2ConverterFactory())
            .baseUrl(TENCENT_PREFIX).build();

    public static Api api = retrofit.create(Api.class);


    /**
     * 获取盘口数据
     **/
    public static StockPanKouData getPanKouData(String q) throws IOException {
        String result = Retrofits.execute(api.getStackBaseInfo(q));
        result = result.substring(result.indexOf("=") + 2, result.indexOf(";") - 1);
        String[] datas = result.split("~");
        StockPanKouData stockPanKouData = new StockPanKouData();
        stockPanKouData.setStockCode(datas[2]);
        stockPanKouData.setStockName(datas[1]);
        stockPanKouData.setCurrentPrice(Double.valueOf(datas[3]));
        stockPanKouData.setYesPrice(Double.valueOf(datas[4]));
        stockPanKouData.setOpenPrice(Double.valueOf(datas[5]));
        stockPanKouData.setExchangeTotal(Double.valueOf(datas[6]) / 10000);
        stockPanKouData.setUpDownMount(Double.valueOf(datas[31]));
        stockPanKouData.setUpDownMountPercent(Double.valueOf(datas[32]));

        stockPanKouData.setLiangBi(Double.valueOf(datas[49]));
        stockPanKouData.setAmplitude(datas[43] + "%");
        stockPanKouData.setB1Price(Double.valueOf(datas[9]));
        stockPanKouData.setB1Number(Double.valueOf(datas[10]));
        stockPanKouData.setB2Price(Double.valueOf(datas[11]));
        stockPanKouData.setB2Number(Double.valueOf(datas[12]));
        stockPanKouData.setB3Price(Double.valueOf(datas[13]));
        stockPanKouData.setB3Number(Double.valueOf(datas[14]));
        stockPanKouData.setB4Price(Double.valueOf(datas[15]));
        stockPanKouData.setB4Number(Double.valueOf(datas[16]));
        stockPanKouData.setB5Price(Double.valueOf(datas[17]));
        stockPanKouData.setB5Number(Double.valueOf(datas[18]));
        stockPanKouData.setS1Price(Double.valueOf(datas[19]));
        stockPanKouData.setS1Number(Double.valueOf(datas[20]));
        stockPanKouData.setS2Price(Double.valueOf(datas[21]));
        stockPanKouData.setS2Number(Double.valueOf(datas[22]));
        stockPanKouData.setS3Price(Double.valueOf(datas[23]));
        stockPanKouData.setS3Number(Double.valueOf(datas[24]));
        stockPanKouData.setS4Price(Double.valueOf(datas[25]));
        stockPanKouData.setS4Number(Double.valueOf(datas[26]));
        stockPanKouData.setS5Price(Double.valueOf(datas[27]));
        stockPanKouData.setS5Number(Double.valueOf(datas[28]));

        stockPanKouData.setBowtomPrice(Double.valueOf(datas[48]));
        stockPanKouData.setTopPrice(Double.valueOf(datas[47]));
        stockPanKouData.setMaxPrice(Double.valueOf(datas[33]));
        stockPanKouData.setMiniPrice(Double.valueOf(datas[34]));
        stockPanKouData.setExchangeValue(Double.valueOf(datas[37]) / 10000);
        stockPanKouData.setExchangeRatio(Double.valueOf(datas[38]));
        stockPanKouData.setMarketEarnActive(Double.valueOf(datas[39]));
        stockPanKouData.setOuter(Double.valueOf(datas[7]) / 10000);
        stockPanKouData.setInner(Double.valueOf(datas[8]) / 10000);
        stockPanKouData.setTotalValue(Double.valueOf(datas[45]));
        stockPanKouData.setMarketValue(Double.valueOf(datas[44]));
        return stockPanKouData;
    }

    public static void main(String... args) throws IOException {
            System.out.println(getPanKouData("sh603345"));
    }


}
