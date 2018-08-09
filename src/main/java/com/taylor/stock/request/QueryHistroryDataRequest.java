package com.taylor.stock.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.taylor.common.JsonUtil;
import com.taylor.yicai.entity.PeriodResult;
import com.taylor.yicai.entity.PeriodResultResp;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.util.StringUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 获取彩票历史数据
 *
 * @author taylor
 */
@Slf4j
public class QueryHistroryDataRequest {

    public static List<PeriodResult> queryLatestDataList(String gameId, int count) {
            try {
                String url = "https://www.yc2025.com/Result/GetLotteryResultList?gameID="+gameId+"&pageSize="+count+"&pageIndex=1";
                //发送get请求
                URL serverUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("host", "https://www.yc2025.com");
                conn.setRequestProperty("Referer","https://www.yc2025.com");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
                conn.connect();
                //将返回的输入流转换成字符串
                OutputStream os =conn.getOutputStream();
                os.flush();
                InputStream inputStream = conn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String buffer = bufferedReader.lines().collect(Collectors.joining());
                bufferedReader.close();
                inputStreamReader.close();
                // 释放资源
                inputStream.close();
                if (!StringUtil.isEmpty(buffer)) {
                    Gson gson = new Gson();
                    PeriodResultResp o = gson.fromJson(buffer, new TypeToken<PeriodResultResp>() {}.getType());
                    return o.getList();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    public static void main(String... args) {
            System.out.println(JsonUtil.transfer2JsonString(queryLatestDataList("26",5)));
    }
}