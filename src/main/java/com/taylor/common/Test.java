package com.taylor.common;/**
 * ${author} on 2018/10/19.
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.taylor.common.Constants.STOCK_CODE_MAIN_BAN;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/10/19 17:51
 */
public class Test {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList(STOCK_CODE_MAIN_BAN.split(","));
        Set<String> sets = new HashSet<>();
        for (String string : strings) {
            if (sets.contains(string)) {
                System.out.println(string);
                continue;
            }
            sets.add(string);
        }
    }
}
