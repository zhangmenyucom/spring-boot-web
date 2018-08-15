package com.taylor.java8;/**
 * ${author} on 2018/8/15.
 */

import lombok.val;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Add some description about this class.
 *
 * @author zhangxiaolu
 * @since 2018/8/15 15:22
 */
public class MapTest {
    public static void main(String... args) {
        /**map里面是对原始数据转换为目标数据，具体就是匿名函数appy**/
        val listMapBefore = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
        val listMapAfter = listMapBefore.stream().map(e -> e * 3).collect(Collectors.toList());
        listMapAfter.forEach(System.out::print);
    }
}
