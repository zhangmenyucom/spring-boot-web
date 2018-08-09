package com.taylor.stock.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.taylor.common.JsonUtil;
import com.taylor.yicai.entity.MyOrder;
import com.taylor.yicai.entity.PeriodResultResp;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.taylor.common.Constants.COOKIE;

/**
 * @author taylor
 */
public class MyOrderListRequest {

    public static synchronized List<MyOrder> postOrder(String gameId, int count) {
         GetMethod method = new GetMethod("https://www.yc2025.com/OffcialOtherGame/GetHistoryOrders?gameId="+gameId+"&num="+count);

        StringBuilder stringBuffer = null;
        try {
            HttpClient client = new HttpClient();
            method.setRequestHeader("Referer", "https://www.yc2025.com/OffcialOtherGame/Index/26");
            method.setRequestHeader("Host", "www.yc2025.com");
            method.setRequestHeader("Origin", "https://www.yc2025.com");
            method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            method.setRequestHeader("Cookie", COOKIE);
            //method.setContentChunked(true);
            // 提交表单
            client.executeMethod(method);
            // 字符流转字节流 循环输出，同get解释
            InputStream inputStream = method.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            stringBuffer = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
            Gson gson = new Gson();
            String result=stringBuffer.toString().replace("\\","");
            return  gson.fromJson(result.substring(1,result.length()-1), new TypeToken<List<MyOrder>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    public static void main(String... args) {
        System.out.println(JsonUtil.transfer2JsonString(postOrder("123",1)));
    }
}