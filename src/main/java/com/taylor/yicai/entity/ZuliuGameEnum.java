package com.taylor.yicai.entity;/**
 * ${author} on 2018/8/24.
 */

import com.taylor.common.Shard;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/24 18:12
 */
@AllArgsConstructor
@Getter
public enum ZuliuGameEnum {
    HOUSANMA(20979, "后三码"),
    QIANSANMA(20988, "前三码"),
    ZHONGSAN(20997, "中三码");
    private Integer gameId;
    private String name;

    private static final List<ZuliuGameEnum> list = Arrays.asList(ZuliuGameEnum.values());

    private static final Shard<ZuliuGameEnum> shard = new Shard<>(list, 100000);

    public static ZuliuGameEnum getRandomBetStrategy() {
        ZuliuGameEnum shardInfo = shard.getShardInfo(UUID.randomUUID().toString());
        System.out.println("本期策略码：" + shardInfo.getName());
        return shardInfo;
    }

    public static void main(String... args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(getRandomBetStrategy().name);
        }
    }
}
