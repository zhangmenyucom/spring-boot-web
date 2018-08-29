package com.taylor.yicai.entity;/**
 * ${author} on 2018/8/24.
 */

import com.taylor.common.Shard;

import java.util.*;

import static com.taylor.common.Constants.NUMBER_LIST;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/24 16:52
 */
public class BetZuLiuStategy {
    public  static final Shard<Integer> shard = new Shard<>(NUMBER_LIST, 100000);

    public static String  generateNextNumber() {
        Set<Integer> numberSet = new HashSet<>();
        while (numberSet.size() <2) {
            numberSet.add(shard.getShardInfo(UUID.randomUUID().toString()));
        }
        List<Integer> numerList = new ArrayList<>(NUMBER_LIST);
        //System.out.println("本期杀： " + numberSet.toString());
        numerList.removeAll(numberSet);
        StringBuilder sb = new StringBuilder("");
        numerList.forEach(e -> sb.append(e + "|"));
        String s = sb.toString();
        return s.substring(0, s.length() - 1);
    }

    public static void main(String... args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(generateNextNumber());
        }
    }
}
