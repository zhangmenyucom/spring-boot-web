package com.taylor.stock.request;

import com.taylor.common.Constants;
import com.taylor.common.JsonUtil;
import com.taylor.common.StockBaseInfoResponse;
import com.taylor.entity.stock.StockBaseInfo;
import com.taylor.entity.stock.StockBaseQueryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import tk.mybatis.mapper.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

/**
 * 获取股票基本数据
 *
 * @author taylor
 */
@Slf4j
public class QueryStockBaseDataRequest {

    public static List<StockBaseInfo> queryStockBaseInfo(StockBaseQueryBean stockQueryBean, GetMethod method) {
        try {
            HttpClient client = new HttpClient();
            NameValuePair[] data = {
                    new NameValuePair("from", "pc"),
                    new NameValuePair("os_ver", stockQueryBean.getOs_ver()),
                    new NameValuePair("cuid", stockQueryBean.getCuid()),
                    new NameValuePair("vv", "100"),
                    new NameValuePair("format", "json"),
                    new NameValuePair("stock_code", stockQueryBean.getStockCode()),
                    new NameValuePair("timestamp", Long.valueOf(new Date().getTime()).toString())
            };
            // method使用表单阈值
            method.setQueryString(data);
            // 提交表单
            client.executeMethod(method);
            // 字符流转字节流 循环输出，同get解释
            InputStream inputStream = method.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuider = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuider.append(str);
            }
            if (!StringUtil.isEmpty(stringBuider.toString())) {
                StockBaseInfoResponse stockBaseInfoResponse = JsonUtil.transferToObj(stringBuider.toString(), StockBaseInfoResponse.class);
                if (stockBaseInfoResponse != null && stockBaseInfoResponse.getErrorNo() == 0) {
                    return stockBaseInfoResponse.getData();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static void main(String... args) {
        GetMethod method = new GetMethod("https://gupiao.baidu.com/api/rails/stockbasicbatch");
        StockBaseQueryBean stockBaseQueryBean=new StockBaseQueryBean();
        stockBaseQueryBean.setStockCode("SH600004,SH600006".toLowerCase());
        System.out.println(JsonUtil.transfer2JsonString(queryStockBaseInfo(stockBaseQueryBean, method)));
    }
}