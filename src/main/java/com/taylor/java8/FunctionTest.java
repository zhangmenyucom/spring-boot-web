package com.taylor.java8;/**
 * ${author} on 2018/8/15.
 */

import java.util.function.Function;

/**
 * Add some description about this class.
 *
 * @author zhangxiaolu
 * @since 2018/8/15 15:00
 */
public class FunctionTest {
    public static void main(String... args) {
        Integer name = 1;
        Function<Integer, Integer> fun1 = e -> e * 3;
        /**apply用于对后面参数按函数规则转为前面的参数**/
        System.out.println(fun1.apply(name));
        Function<Integer, Integer> fun2 = e -> e - 2;
        /**andThen有序先执行外面函数再执行里面函数**/
        System.out.println(fun1.andThen(fun2).apply(name));

        /**compose先用外面的参数去执行里面的函数，然后返回的结果再去执行外面的函数**/
        System.out.println(fun1.compose(fun2).apply(name));

    }
}
