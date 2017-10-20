package com.taylor.builder;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/19 17:13
 */
public class Client {
    public static void main(String... args) {
        BenzCar benzCar = new BenzCar();
        BWMCar bwmCar = new BWMCar();
        benzCar.run();
        bwmCar.run();

        BenCarBuiler benCarBuiler=new BenCarBuiler();
        benCarBuiler.build();
        BMWCarBuiler bmwCarBuiler=new BMWCarBuiler();
        bmwCarBuiler.build();
    }
}
