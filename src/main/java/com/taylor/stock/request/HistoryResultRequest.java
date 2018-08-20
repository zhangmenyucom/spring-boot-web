package com.taylor.stock.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.taylor.common.JsonUtil;
import com.taylor.yicai.entity.PeriodResultResp;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static com.taylor.common.Constants.BASE_URL;
import static com.taylor.common.Constants.COOKIE;

/**
 * @author taylor
 */
public class HistoryResultRequest {

    public static synchronized PeriodResultResp postOrder(String gameId, int count) {
        GetMethod method = new GetMethod(BASE_URL+"/Result/GetLotteryResultList?gameID=" + gameId + "&pagesize=" + count + "&pageIndex=1");

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
            if (stringBuffer.contains("total")) {
                Gson gson = new Gson();
                String result = stringBuffer.replace("\\", "");
                return gson.fromJson(result, new TypeToken<PeriodResultResp>() {
                }.getType());
            } else {
                Thread.sleep(5000);
                return postOrder(gameId, count);
            }
        } catch (IOException | InterruptedException e) {
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
            for (int i=2;i<split.length;i++) {
                if (countMap.get(split[i]) != null) {
                    countMap.put(split[i], countMap.get(split[i]) + 1);
                } else {
                    countMap.put(split[i], 1);
                }
            }
        });
        // 升序比较器
        Comparator<Map.Entry<String, Integer>> valueComparator = Comparator.comparingInt(Map.Entry::getValue);
        // map转换成list进行排序
        List<Map.Entry<String, Integer>> list = new ArrayList<>(countMap.entrySet());

        // 排序
        Collections.sort(list, valueComparator);

        return list.stream().map(entry -> Integer.valueOf(entry.getKey())).collect(Collectors.toList());
    }

    public static String getNextContent(String gameId, int count) {
        List<Integer> lenMenHaoList = getLenMenHao(gameId, count);
        System.out.println(JsonUtil.transfer2JsonString(lenMenHaoList));
        String template = "0|1|2|3|4|5|6|7|8|9|";
        Integer first = lenMenHaoList.get(0);
        Integer second = lenMenHaoList.get(1);
        String tem = template.replace(first + "|", "").replace(second + "|", "");
        return tem.substring(0, tem.length() - 1);
    }


    public static void main(String... args) {
        System.out.println(JsonUtil.transfer2JsonString(getNextContent("123", 45)));
    }
}