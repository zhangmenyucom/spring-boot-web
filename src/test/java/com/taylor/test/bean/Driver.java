package com.taylor.test.bean;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/16 15:55
 */
public class Driver implements IDriver {
    @Override
    public void drive(ICar car) {
        System.out.println("I am driving ....");
        car.run();
    }
}
