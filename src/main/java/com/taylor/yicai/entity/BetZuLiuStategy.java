package com.taylor.yicai.entity;/**
 * ${author} on 2018/8/24.
 */

import com.taylor.common.Shard;
import com.taylor.stock.request.HistoryResultRequest;

import java.util.*;

import static com.taylor.common.Constants.GAMEID;
import static com.taylor.common.Constants.NUMBER_LIST;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/24 16:52
 */
public class BetZuLiuStategy {
    public static final Shard<Integer> shard = new Shard<>(NUMBER_LIST, 100000);

    public static String generateNextNumber() {

        List<Integer> numerList = new ArrayList<>(NUMBER_LIST);
        String shaNumber = getShaNumber();
        if(shaNumber !=null){
            numerList.remove(Integer.valueOf(shaNumber));
        }else {
            Set<Integer> numberSet = new HashSet<>();
            while (numberSet.size() < 1) {
                numberSet.add(shard.getShardInfo(UUID.randomUUID().toString()));
            }
            //System.out.println("本期杀： " + numberSet.toString());
            numerList.removeAll(numberSet);
        }
        StringBuilder sb = new StringBuilder("");
        numerList.forEach(e -> sb.append(e + "|"));
        String s = sb.toString();
        return s.substring(0, s.length() - 1);
    }

    public static void main(String... args) {

        System.out.println(generateNextNumber());
    }

    public static String getShaNumber() {
        PeriodResultResp periodResultResp = HistoryResultRequest.postOrder(GAMEID, 10);
        List<PeriodResult> list = periodResultResp.getList();
        for (PeriodResult periodResult : list) {
            Set<String> set = new HashSet<>();
            for (String s : Arrays.asList(periodResult.getResult().split(","))) {
                if (set.contains(s)) {
                    return s;
                }else{
                    set.add(s);
                }
            }
        }
        return null;
    }
}
