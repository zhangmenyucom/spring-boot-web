package com.taylor;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/18 11:00
 */
public class WhriteHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("I am white");
    }

    @Override
    public void talk() {

        System.out.println("this is white speaking");
    }
}
