package com.taylor.proxy;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/20 10:25
 */
public class Game implements IGame {

    @Override
    public void login() {
        System.out.println("log in the game");
    }

    @Override
    public void killBoss() {
        System.out.println("kill boss...");
    }

    @Override
    public void upgrade() {
        System.out.println("upgrade the game ....");
    }
}
