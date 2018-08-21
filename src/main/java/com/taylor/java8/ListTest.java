package com.taylor.java8;/**
 * ${author} on 2018/8/21.
 */



import lombok.val;

import java.util.Arrays;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/21 11:04
 */

public class ListTest {
    public static void main(String[] args) {
        Integer s=1;
        val list= Arrays.asList(s,2,2,2);
        long count = list.stream().filter(i->i<2).count();
        System.out.println(count);
    }
}
