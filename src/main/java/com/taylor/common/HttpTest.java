package com.taylor.common;


import com.taylor.entity.stock.GuZhenResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * taylor.zhang
 */
public class HttpTest {

    protected static void doGet(HttpGet httpGet) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        //设置连接超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setConnectionRequestTimeout(10000).setSocketTimeout(10000).setRedirectsEnabled(true).build();
        httpGet.setConfig(requestConfig);
        httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet.addHeader("Accept-Encoding", "gzip, deflate");
        httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8,sm;q=0.6");
        httpGet.addHeader("Cache-Control", "max-age=0");
        httpGet.addHeader("Connection", "keep-alive");
        httpGet.addHeader("Cookie", "v=AiS60RKJIgR4MlZILPrLgY5l9SkSvUgnCuHcaz5FsO-y6c4fZs0Yt1rxrPqN; cid=8470bb9a08c7b9b6e03c64855014bab61519633103; ComputerID=8470bb9a08c7b9b6e03c64855014bab61519633103");
        httpGet.addHeader("Host", "www.iwencai.com");
        httpGet.addHeader("Upgrade-Insecure-Requests", "1");
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");

        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            String resultJson = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            GuZhenResponse guZhenResponse = JsonUtil.transferToObj(resultJson, GuZhenResponse.class);
            System.out.println(JsonUtil.transfer2JsonString(guZhenResponse));

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String... args) throws IOException {
        for (int i = 0; i < 100; i++) {

            String host = "http://www.iwencai.com/diag/block-detail?pid=8093&codes=002839&codeType=stock&info=%7B%22view%22%3A%7B%22nolazy%22%3A1%2C%22parseArr%22%3A%7B%22_v%22%3A%22new%22%2C%22dateRange%22%3A%5B%5D%2C%22staying%22%3A%5B%5D%2C%22queryCompare%22%3A%5B%5D%2C%22comparesOfIndex%22%3A%5B%5D%7D%2C%22asyncParams%22%3A%7B%22tid%22%3A137%7D%7D%7D";
            HttpGet httpGet = new HttpGet(host);
            doGet(httpGet);
        }
    }


}
