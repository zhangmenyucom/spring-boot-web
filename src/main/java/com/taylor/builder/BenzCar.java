package com.taylor.builder;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/19 17:11
 */
public class BenzCar extends CarModelTemplate {
    @Override
    void start() {
        System.out.println("benzcar start.....");
    }

    @Override
    void stop() {
        System.out.println("benzcar stop.....");
    }

    @Override
    boolean alarm() {
        return true;
    }

    @Override
    void boom() {
        System.out.println("benzcar boom.....");
    }
}
