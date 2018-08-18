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
import java.util.*;
import java.util.stream.Collectors;

import static com.taylor.common.Constants.COOKIE;

/**
 * @author taylor
 */
public class HistoryResultRequest {

    public static synchronized PeriodResultResp postOrder(String gameId, int count) {
        GetMethod method = new GetMethod("https://www.yc2025.com/Result/GetLotteryResultList?gameID=" + gameId + "&pagesize=" + count + "&pageIndex=1");

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
            String result = stringBuffer.toString().replace("\\", "");
            return gson.fromJson(result, new TypeToken<PeriodResultResp>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static List<Integer> getLenMenHao(String gameId, int count) {
        PeriodResultResp periodResultResp = postOrder(gameId, count);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TreeMap<String, Integer> countMap = new TreeMap<>();
        periodResultResp.getList().forEach(result -> {
            String[] split = result.getResult().replace("\"", "").split(",");
            for (String s : split) {
                if (countMap.get(s) != null) {
                    countMap.put(s, countMap.get(s) + 1);
                } else {
                    countMap.put(s, 1);
                }
            }
        });
        // 升序比较器
        Comparator<Map.Entry<String, Integer>> valueComparator = (o1, o2) -> {
            return o1.getValue() - o2.getValue();
        };
        // map转换成list进行排序
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(countMap.entrySet());

        // 排序
        Collections.sort(list, valueComparator);

        return list.stream().map(entry->Integer.valueOf(entry.getKey())).collect(Collectors.toList());
    }

    public static String getNextContent(String gameId, int count) {
        List<Integer> lenMenHaoList = getLenMenHao("123", 20);
        System.out.println(JsonUtil.transfer2JsonString(lenMenHaoList));
        String template = "0|1|2|3|4|5|6|7|8|9|";
        Integer first=lenMenHaoList.get(0);
        Integer second=lenMenHaoList.get(1);
        String tem=template.replace(first + "|", "").replace(second + "|", "");
        return tem.substring(0,tem.length()-1);

    }


    public static void main(String... args) {
        System.out.println(JsonUtil.transfer2JsonString(getNextContent("123", 45)));
    }
}