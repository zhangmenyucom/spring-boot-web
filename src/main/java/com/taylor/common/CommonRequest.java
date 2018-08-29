package com.taylor.common;

import com.taylor.entity.TongHuaShunStockBase;
import com.taylor.entity.stock.StockFundInOut;
import com.taylor.entity.stock.TencentDayData;
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
import java.util.Map;


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

    public static void main(String... args) {
    }
}
