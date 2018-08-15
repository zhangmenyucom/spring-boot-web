package com.taylor.java8;/**
 * ${author} on 2018/8/15.
 */

import java.util.function.Predicate;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/15 16:02
 */
public class PredicateTest {

    public static void main(String... args) {

        Predicate<Integer> predicate=e->e>4;

        /**判断给定的参数是否符合指定的规则**/
        System.out.println(predicate.test(5));

        /**取反**/
        System.out.println(predicate.negate().test(5));

        /**同一个参数重合条件**/
        System.out.println(predicate.and(predicate).test(6));

        /**或条件**/
        System.out.println(predicate.or(predicate).and(predicate).test(7));

    }
}
