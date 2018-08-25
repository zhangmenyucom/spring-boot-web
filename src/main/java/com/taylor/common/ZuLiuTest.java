package com.taylor.common;

import com.taylor.yicai.entity.BetZuLiuStategy;
import com.taylor.yicai.entity.Order;
import com.taylor.yicai.entity.ZuliuGameEnum;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/8/25 12:40
 */
public class ZuLiuTest {
    public static void main(String... args) {
        int countTrue = 0;
        int countFalse = 0;
        int maxFalse = 0;
        int continueFalse = 0;
        for (int i = 1; i <= 120; i++) {
            Order zuliuOrder = Order.getZuliuOrder(1);
            List<String> list = Arrays.asList(zuliuOrder.getC().split("\\|"));
            String strategyName = ZuliuGameEnum.getStrategyName(zuliuOrder.getI());
            System.out.print("第" + i + "期预测：" + strategyName + ":" + zuliuOrder.getC().replaceAll("\\|", ","));
            List<Integer> periodResult = getPeriodResult();
            System.out.print(" 结果:" + periodResult.toString());
            boolean result = false;
            if (strategyName.contains("前")) {
                if (list.contains(periodResult.get(0).toString()) && list.contains(periodResult.get(1).toString()) && list.contains(periodResult.get(2).toString())) {
                    if (!Objects.equals(periodResult.get(0), periodResult.get(1)) && !Objects.equals(periodResult.get(1), periodResult.get(2)) && !Objects.equals(periodResult.get(0), periodResult.get(3))) {
                        result = true;
                    }
                }
            }
            if (strategyName.contains("中")) {
                if (list.contains(periodResult.get(1).toString()) && list.contains(periodResult.get(2).toString()) && list.contains(periodResult.get(3).toString())) {
                    if (!Objects.equals(periodResult.get(1), periodResult.get(2)) && !Objects.equals(periodResult.get(2), periodResult.get(3)) && !Objects.equals(periodResult.get(1), periodResult.get(3))) {
                        result = true;
                    }
                }
            }
            if (strategyName.contains("后")) {
                if (list.contains(periodResult.get(2).toString()) && list.contains(periodResult.get(3).toString()) && list.contains(periodResult.get(4).toString())) {
                    if (!Objects.equals(periodResult.get(2), periodResult.get(3)) && !Objects.equals(periodResult.get(3), periodResult.get(4)) && !Objects.equals(periodResult.get(2), periodResult.get(4))) {
                        result = true;
                    }
                }
            }
            System.out.println(result);
            if (result) {
                if (maxFalse < continueFalse) {
                    maxFalse = continueFalse;
                }
                continueFalse = 0;
                countTrue++;
            } else {
                countFalse++;
                continueFalse++;
            }

        }
        if (continueFalse > maxFalse) {
            maxFalse = continueFalse;
        }
        System.out.println("TrueCount:" + countTrue);
        System.out.println("CountFalse:" + countFalse);
        System.out.println("continueFalse:" + maxFalse);
    }

    public static List<Integer> getPeriodResult() {
        return IntStream.range(0, 5).mapToObj(i -> BetZuLiuStategy.shard.getShardInfo(UUID.randomUUID().toString())).collect(Collectors.toList());
    }

}
