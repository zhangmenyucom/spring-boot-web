package com.taylor;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/18 11:00
 */
public class BlackHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("I am black");
    }

    @Override
    public void talk() {

        System.out.println("this is black speaking");
    }
}
