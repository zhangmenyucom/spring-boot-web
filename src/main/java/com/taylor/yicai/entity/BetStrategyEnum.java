package com.taylor.yicai.entity;/**
 * ${author} on 2018/8/9.
 */

import com.taylor.common.Shard;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.SecureRandom;
import java.util.*;

/**
 * Add some description about this class.
 *
 * @author zhangxiaolu
 * @since 2018/8/9 9:32
 */
@AllArgsConstructor
@Getter
public enum BetStrategyEnum {
    /*DAN_SHUANG("单|双", 1),
    DAN_DA("单|大", 1),
    DAN_XIAO("单|小", 1),
    DAN_DAN("单|单", 1),
    SHUANG_DA("双|大", 1),
    SHUANG_XIAO("双|小", 1),
    SHUANG_DAN("双|单", 1),
    SHUANG_SHUANG("双|双", 1),
    DA_SHUANG("大|双", 1),
    DA_DA("大|大", 1),
    DA_XIAO("大|小", 1),
    DA_DAN("大|单", 1),
    XIAO_SHUANG("小|双", 1),
    XIAO_DA("小|大", 1),
    XIAO_XIAO("小|小", 1),
    XIAO_DAN("小|单", 1),*/

    DANSHUANG_SHUANG("单,双|双", 2),
    DANSHUANG_DAN("单,双|单", 2),
    DANSHUANG_DA("单,双|大", 2),
    DANSHUANG_XIAO("单,双|小", 2),


    DAN_DANSHUANG("单|单,双", 2),
    DA_DANSHUANG("大|单,双", 2),
    XIAO_DANSHUANG("小|单,双", 2),
    SHUANG_DANSHUANG("双|单,双", 2),


    DAXIAO_DA("大,小|大", 2),
    DAXIAO_DAN("大,小|单", 2),
    DAXIAO_SHUANG("大,小|双", 2),
    DAXIAO_XIAO("大,小|小", 2),


    DA_DAXIAO("大|大,小", 2),
    SHUANG_DAXIAO("双|大,小", 2),
    XIAO_DAXIAO("小|大,小", 2),
    DAN_DAXIAO("单|大,小", 2);


    private String content;

    private int n;

    private static final List<BetStrategyEnum> list = Arrays.asList(BetStrategyEnum.values());

    private static final Shard<BetStrategyEnum> shard=new Shard<>(list,10000);


    public static BetStrategyEnum getRandomBetStrategy() {
        return shard.getShardInfo(UUID.randomUUID().toString());
    }
    public static void main(String... args) {
        Map<String,Integer> map=new HashMap<>();
        Arrays.stream(BetStrategyEnum.values()).forEach(value-> map.put(value.getContent(),0));
        for (int i = 0; i < 1000; i++) {
            String content = getRandomBetStrategy().getContent();
            map.put(content,map.get(content)+1);
        }
        map.entrySet().forEach(entry-> System.out.println(entry.getKey()+":"+entry.getValue()));
    }
}
