package com.taylor.algo.grade;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/8/6 23:31
 */
public class Sanjiao {

    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            for (int j = i + 1; j < 100; j++) {
                for (int k = j + 1; k <= 100; k++) {
                    if (i * i + j * j == k * k) {
                        System.out.println("三角数为(" + i + "," + j + "," + k + ")");
                    }
                }
            }
        }
    }
}
