package com.taylor.algo.grade;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/24 15:34
 */
public class Demo {

    public static int getCount(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 3;
        }
        return getCount(n-3)+getCount(n - 2) + getCount(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(getCount(20));
    }
}

