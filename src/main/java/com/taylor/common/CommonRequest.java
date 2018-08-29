package com.taylor.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import tk.mybatis.mapper.util.StringUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


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

    public static void main(String... args) {
    }
}
