package com.taylor.java8;/**
 * ${author} on 2018/8/15.
 */

import lombok.val;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/15 15:42
 */
public class FilterTest {
    public static void main(String... args) {
        /**fitler主要用作数据过滤用**/
        val list = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
        val after = list.stream().filter(e -> e % 2 == 0).filter(e -> e % 3 == 0).collect(Collectors.toList());
        after.forEach(System.out::println);
    }
}
