package com.taylor.common;

import com.taylor.entity.stock.StockFundInOut;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.entity.stock.TencentDayData;
import com.taylor.entity.stock.TencentTodayBaseInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import tk.mybatis.mapper.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/10 1:37
 */
@Slf4j
public class CommonRequest<T> {

    static final ThreadLocal<Integer> retryCount = new ThreadLocal<>();

    public synchronized String executeRequest(T in, HttpMethodBase method) {
        if (retryCount.get() == null) {
            retryCount.set(0);
        }
        if (in == null) {
            return null;
        }
        NameValuePair[] data = new ObjectToNameValuePairUtil<T>().transferObj2NameParis(in);
        StringBuilder stringBuider = new StringBuilder();
        try {
            HttpClient client = new HttpClient();
            // method使用表单阈值
            method.setQueryString(data);
            // 提交表单
            client.executeMethod(method);
            // 字符流转字节流 循环输出，同get解释
            InputStream inputStream = method.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String str;
            while ((str = br.readLine()) != null) {
                stringBuider.append(str);
            }
        } catch (Exception e) {
            /**重试两次**/
            if (retryCount.get() <= 2) {
                retryCount.set(retryCount.get() + 1);
                return executeRequest(in, method);
            }
            ProcessCountor.FAIL_COUNT.incrementAndGet();
            log.info("错误e{}", e.getMessage());
        }
        retryCount.set(0);
        if (!StringUtil.isEmpty(stringBuider.toString())) {
            return stringBuider.toString();
        }
        return null;
    }

    /**
     * @param stockCode
     * @desc主力散户资金流入
     */
    public static StockFundInOut getStockFundInOutData(String stockCode) {
        StockFundInOut stockFundInOut = null;
        InputStreamReader isr = null;
        try {
            String url = "http://qt.gtimg.cn/q=ff_" + stockCode.toLowerCase();
            URL u = new URL(url);
            isr = new InputStreamReader(u.openStream(), "GBK");
            char[] cha = new char[1024];
            int len = isr.read(cha);
            String result = new String(cha, 0, len);
            result = result.substring(result.indexOf("=") + 2, result.indexOf(";") - 1);
            String[] datas = result.split("~");
            stockFundInOut = new StockFundInOut();
            stockFundInOut.setDateTime(datas[13]);
            stockFundInOut.setMainIn(Double.valueOf(datas[1]));
            stockFundInOut.setStockCode(datas[0]);
            stockFundInOut.setMainOut(Double.valueOf(datas[2]));
            stockFundInOut.setMainTotalIn(Double.valueOf(datas[3]));
            stockFundInOut.setMainInBi(Double.valueOf(datas[4]));
            stockFundInOut.setRetailIn(Double.valueOf(datas[5]));
            stockFundInOut.setRetailOut(Double.valueOf(datas[6]));
            stockFundInOut.setRetailTotalIn(Double.valueOf(datas[7]));
            stockFundInOut.setRetailInBi(Double.valueOf(datas[8]));
            stockFundInOut.setTotalIN(Double.valueOf(datas[9]));
            stockFundInOut.setStockName(datas[12]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stockFundInOut;
    }


    /**
     * @param stockCode
     * @desc 股票盘口数据
     */
    public static StockPanKouData getStockPanKouData(String stockCode) {
        StockPanKouData stockPanKouData = null;
        InputStreamReader isr = null;
        try {
            String url = "http://qt.gtimg.cn/q=" + stockCode.toLowerCase();
            URL u = new URL(url);
            isr = new InputStreamReader(u.openStream(), "GBK");
            char[] cha = new char[1024];
            int len = isr.read(cha);
            String result = new String(cha, 0, len);
            result = result.substring(result.indexOf("=") + 2, result.indexOf(";") - 1);
            String[] datas = result.split("~");
            stockPanKouData = new StockPanKouData();
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stockPanKouData;
    }

    public static List<TencentDayData> getStckDailyHistory(String stockCode, int count) {
        List<TencentDayData> list = new ArrayList<>();
        try {
            String url = "http://web.ifzq.gtimg.cn/appstock/app/kline/kline?param=" + stockCode + ",day,,," + count;
            URL u = new URL(url);
            InputStreamReader isr = new InputStreamReader(u.openStream(), "GBK");
            char[] cha = new char[10240];
            int len = isr.read(cha);
            String result = new String(cha, 0, len);
            if (result != null) {
                result = result.substring(result.indexOf("day") + 6, result.indexOf("qt") - 3).replace("[", "").replace("\"", "");
            }
            String[] dadaDaliyList = result.split("],");
            for (String dailyData : dadaDaliyList) {
                String[] dataDetailArray = dailyData.replace("]", "").split(",");
                TencentDayData tencentDayData = new TencentDayData();
                tencentDayData.setDate(dataDetailArray[0]);
                tencentDayData.setOpen(Double.valueOf(dataDetailArray[1]));
                tencentDayData.setClose(Double.valueOf(dataDetailArray[2]));
                tencentDayData.setHigh(Double.valueOf(dataDetailArray[3]));
                tencentDayData.setLow(Double.valueOf(dataDetailArray[4]));
                tencentDayData.setTotalHands(Double.valueOf(dataDetailArray[5]).longValue());
                list.add(tencentDayData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static TencentTodayBaseInfo getStckTodayBaseInfo(String stockCode) {
        TencentTodayBaseInfo tencentTodayBaseInfo=new TencentTodayBaseInfo();
        try {
            String url = "http://web.sqt.gtimg.cn/q=" + stockCode;
            URL u = new URL(url);
            InputStreamReader isr = new InputStreamReader(u.openStream(), "GBK");
            char[] cha = new char[10240];
            int len = isr.read(cha);
            String result = new String(cha, 0, len);
            String[] dataInfoArray=result.split("~");
            tencentTodayBaseInfo.setStockName(dataInfoArray[1]);
            tencentTodayBaseInfo.setClose(Double.valueOf(dataInfoArray[3]));
            tencentTodayBaseInfo.setPreClose(Double.valueOf(dataInfoArray[4]));
            tencentTodayBaseInfo.setOpen(Double.valueOf(dataInfoArray[5]));
            tencentTodayBaseInfo.setTotalHands(Double.valueOf(dataInfoArray[6]).longValue());
            tencentTodayBaseInfo.setUpDownValue(Double.valueOf(dataInfoArray[31]));
            tencentTodayBaseInfo.setUpDownValue(Double.valueOf(dataInfoArray[31]));
            tencentTodayBaseInfo.setUpDownPercent(Double.valueOf(dataInfoArray[32]));
            tencentTodayBaseInfo.setHigh(Double.valueOf(dataInfoArray[33]));
            tencentTodayBaseInfo.setLow(Double.valueOf(dataInfoArray[34]));
            tencentTodayBaseInfo.setExchangeRatio(Double.valueOf(dataInfoArray[37]));
            tencentTodayBaseInfo.setLiangbi(Double.valueOf(dataInfoArray[49]));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tencentTodayBaseInfo;
    }

    public static void main(String... args) {
        System.out.println(JsonUtil.transfer2JsonString(getStckTodayBaseInfo("sz002839")));
    }
}
