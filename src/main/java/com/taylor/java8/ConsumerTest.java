package com.taylor.java8;/**
 * ${author} on 2018/8/15.
 */

import java.util.function.Consumer;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/15 16:22
 */
public class ConsumerTest {
    public static void main(String... args) {

        /**consume与function的区别是就是function是有返回值的，consumer没有返回值**/
        Consumer<String> consumer = System.out::println;

        consumer.accept("1231231");

    }
}
