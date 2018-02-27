package com.taylor.stock.request;

import com.taylor.common.JsonUtil;
import com.taylor.entity.stock.GuZhenResponse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author taylor
 */
public class GuZhenRequest {
    public static GuZhenResponse getGuZhengData(String stockCode) {
        try {
            StringBuilder buffer = new StringBuilder();

            String url = "http://www.iwencai.com/diag/block-detail?pid=8093&codes=" + stockCode.substring(2, stockCode.length()) + "&codeType=stock&info=%7B%22view%22%3A%7B%22nolazy%22%3A1%2C%22parseArr%22%3A%7B%22_v%22%3A%22new%22%2C%22dateRange%22%3A%5B%5D%2C%22staying%22%3A%5B%5D%2C%22queryCompare%22%3A%5B%5D%2C%22comparesOfIndex%22%3A%5B%5D%7D%2C%22asyncParams%22%3A%7B%22tid%22%3A137%7D%7D%7D";

            //发送get请求
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            conn.setRequestProperty("Accept-Encoding", "deflate");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,sm;q=0.6");
            conn.setRequestProperty("Cache-Control", "max-age=0");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Cookie", "PHPSESSID=46b9c90167702d451cb4fa84b0771684; other_uid=Ths_iwencai_Xuangu_5m85bhf2vmfu1noswk6h33ytdjbjygm6; other_uname=ex81yaeb3t; v=AnytUAEaCppDUT5GFOCYSXwVTRErdSCfohk0Y1b9iGdKIRYFfoXwL_IpBPKl; cid=c582919adf61966f0a14800bae2226821515742096; ComputerID=c582919adf61966f0a14800bae2226821515742096");
            conn.setRequestProperty("Host", "www.iwencai.com");
            conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
            conn.connect();

            //将返回的输入流转换成字符串
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            return JsonUtil.transferToObj(buffer.toString(), GuZhenResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String... args) {
        GuZhenResponse guZhenResponse=getGuZhengData("sz002302");
        System.out.println(guZhenResponse.getData().getData().getResult().get_score());
    }
}