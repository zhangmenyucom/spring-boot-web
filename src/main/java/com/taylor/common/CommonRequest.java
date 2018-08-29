package com.taylor.common;

import com.taylor.api.ApiClient;
import com.taylor.entity.TongHuaShunStockBase;
import com.taylor.entity.stock.*;
import com.taylor.entity.stock.query.StockQueryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import tk.mybatis.mapper.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.taylor.common.Constants.METHOD_URL_STOCK_DAY_INFO;


/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/10 1:37
 */
@Slf4j
public class CommonRequest<T> {

    static final ThreadLocal<Integer> retryCount = new ThreadLocal<>();

    public String executeRequest(T in, HttpMethodBase method) {
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
                log.info("错误，重试e{}", e.getMessage());
                retryCount.set(retryCount.get() + 1);
                return executeRequest(in, method);
            }
            log.info("错误{}", e.getMessage());
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
            if (result.contains("pv_none_match=1")) {
                stockFundInOut = new StockFundInOut();
                stockFundInOut.setStatus(-1);
                return stockFundInOut;
            }
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
            System.out.println("主力散户资金流入出错" + e.getMessage());
            return stockFundInOut;
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
     * @desc 股票同花顺数据
     */
    public static TongHuaShunStockBase getBaseDataFromTongHuaShun(String stockCode) {
        TongHuaShunStockBase tongHuaShunStockBase = new TongHuaShunStockBase();
        tongHuaShunStockBase.setStockCode(stockCode);
        InputStreamReader isr = null;
        try {
            String url = "http://qd.10jqka.com.cn/quote.php?cate=real&type=stock&return=json&callback=showStockData&code=" + stockCode.substring(2);
            URL u = new URL(url);
            isr = new InputStreamReader(u.openStream(), "GBK");
            char[] cha = new char[1024];
            int len = isr.read(cha);
            String result = new String(cha, 0, len);
            Map<String, Object> stockMap = JsonUtil.readJson2Map(result.substring(result.indexOf("(") + 1, result.indexOf(")")));
            Map<String, Object> info = (Map<String, Object>) stockMap.get("info");
            Map<String, Object> nameMap = (Map<String, Object>) info.get(stockCode.substring(2));
            Map<String, Object> data = (Map<String, Object>) stockMap.get("data");
            Map<String, Object> dataStockMap = (Map<String, Object>) data.get(stockCode.substring(2));
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
        } catch (Exception e) {
            System.out.println("股票同花顺数据" + e.getMessage());
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tongHuaShunStockBase;
    }

    /**
     * @param stockCode
     * @desc 股票同花顺数据
     */
    public static void test(String stockCode) {
        TongHuaShunStockBase tongHuaShunStockBase = new TongHuaShunStockBase();
        tongHuaShunStockBase.setStockCode(stockCode);
        InputStreamReader isr = null;
        try {
            String url = "http://d.10jqka.com.cn/v2/realhead/hs_002839/last.js";
            URL u = new URL(url);
            System.out.println(u.getRef());
            isr = new InputStreamReader(u.openStream(), "GBK");
            char[] cha = new char[1024];
            int len = isr.read(cha);
            String result = new String(cha, 0, len);
            System.out.println(result);
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
    }

    public static List<TencentDayData> getStckDailyHistory(String stockCode, int count) {
        List<TencentDayData> list = new ArrayList<>();
        try {
            String url = "http://web.ifzq.gtimg.cn/appstock/app/kline/kline?param=" + stockCode.toLowerCase() + ",day,,," + count;
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


    public static void main(String... args) {
    }
}
