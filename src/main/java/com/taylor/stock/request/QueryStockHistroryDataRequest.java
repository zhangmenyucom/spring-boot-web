package com.taylor.stock.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.taylor.common.JsonUtil;
import com.taylor.entity.stock.HistoryData;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.util.StringUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * 获取股票数据
 *
 * @author taylor
 */
@Slf4j
public class QueryStockHistroryDataRequest {

    public static List<HistoryData> queryLatestDataList(String stockCode, int count) {
            try {
                StringBuilder buffer = new StringBuilder();
                String url = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol="+stockCode+"&scale=240&ma=240&datalen="+count;
                //发送get请求
                URL serverUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("host", "http://money.finance.sina.com.cn");
                conn.setRequestProperty("Referer","http://money.finance.sina.com.cn");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
                conn.connect();
                //将返回的输入流转换成字符串
                OutputStream os =conn.getOutputStream();
                os.flush();
                InputStream inputStream = conn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                bufferedReader.close();
                inputStreamReader.close();
                // 释放资源
                inputStream.close();
                if (!StringUtil.isEmpty(buffer.toString())) {
                    Gson gson = new Gson();
                    return  gson.fromJson(buffer.toString(), new TypeToken<List<HistoryData>>(){}.getType());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    public static void main(String... args) {
        for (int i = 0; i < 100; i++) {

            System.out.println(JsonUtil.transfer2JsonString(queryLatestDataList("sz000001",10)));
        }
    }
}