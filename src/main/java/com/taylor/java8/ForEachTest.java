package com.taylor.java8;/**
 * ${author} on 2018/8/15.
 */

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Add some description about this class.
 *
 * @author zhangxiaolu
 * @since 2018/8/15 9:30
 */
public class ForEachTest {
    public static void main(String... args) {
        List<Integer> list1 = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
        List<Integer> list2 = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
        List<Integer> list3 = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
        list1.forEach(i -> list2.forEach(j ->list3.forEach(k -> {
            if (k > j && j > i && k * k == i * i + j * j) {
                System.out.println("(" + i + "," + j + "," + k + ")");
            }
        })));
    }
}
