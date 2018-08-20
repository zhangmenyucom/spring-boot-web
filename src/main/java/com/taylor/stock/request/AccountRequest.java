package com.taylor.stock.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.taylor.common.JsonUtil;
import com.taylor.yicai.entity.Account;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static com.taylor.common.Constants.BASE_URL;
import static com.taylor.common.Constants.COOKIE;

/**
 * @author taylor
 */
public class AccountRequest {

    public static synchronized Account getAccount() {
        GetMethod method = new GetMethod(BASE_URL+"/AccountInfo/GetAccount");

        try {
            HttpClient client = new HttpClient();
            method.setRequestHeader("Referer", BASE_URL+"/OffcialOtherGame/Index/26");
            method.setRequestHeader("Host", BASE_URL);
            method.setRequestHeader("Origin", BASE_URL);
            method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            method.setRequestHeader("Cookie", COOKIE);
            //method.setContentChunked(true);
            // 提交表单
            client.executeMethod(method);
            // 字符流转字节流 循环输出，同get解释
            InputStream inputStream = method.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String stringBuffer = br.lines().collect(Collectors.joining());
            if (stringBuffer.contains("CreditBalance")) {
                Gson gson = new Gson();
                String result = stringBuffer.replace("\\", "");
                return gson.fromJson(result, new TypeToken<Account>() {
                }.getType());
            } else {
                Thread.sleep(5000);
                return getAccount();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;

    }


    public static void main(String... args) {
        System.out.println(JsonUtil.transfer2JsonString(getAccount()));
    }
}