package com.taylor.yicai.entity;/**
 * ${author} on 2018/8/20.
 */

import com.taylor.common.Shard;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.SecureRandom;
import java.util.*;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/20 18:18
 */
@Getter
@AllArgsConstructor
public enum BetGameEnum {
    QIANER_DAN_SHUANG(21024, "前二单双"),
    HOUER_DAN_SHUANG(21023, "后二单双");
    private int gameId;
    private String name;
    private static final List<BetGameEnum> list = Arrays.asList(BetGameEnum.values());
    private static final SecureRandom secureRandom = new SecureRandom();

    private static final Shard<BetGameEnum> shard=new Shard<>(list,1000000);

    public static BetGameEnum getRandomBetGame() {
        return shard.getShardInfo("SHARD-"+new Date().getTime()+""+secureRandom.nextInt(Integer.MAX_VALUE)+"-NODE-");
    }

    public static void main(String... args) {
        Map<Integer,Integer> map=new HashMap<>(2);
        map.put(QIANER_DAN_SHUANG.getGameId(),0);
        map.put(HOUER_DAN_SHUANG.getGameId(),0);
        for (int i = 0; i < 10000; i++) {
            int gameId = getRandomBetGame().getGameId();
            map.put(gameId,map.get(gameId)+1);
        }
        System.out.println(map.get(QIANER_DAN_SHUANG.getGameId()));
        System.out.println(map.get(HOUER_DAN_SHUANG.getGameId()));
    }
}
