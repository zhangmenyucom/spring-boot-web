package com.taylor.builder;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/19 17:11
 */
public class BWMCar extends CarModelTemplate {
    @Override
    void start() {
        System.out.println("bwm start.....");
    }

    @Override
    void stop() {
        System.out.println("bwm stop.....");
    }

    @Override
    boolean alarm() {
        return false;
    }

    @Override
    void boom() {
        System.out.println("bwm boom.....");
    }
}
