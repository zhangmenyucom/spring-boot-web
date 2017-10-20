package com.taylor.thread.classlock;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/20 17:48
 */
public class Consumer extends Thread {
    private MenuHolder menuHolder;

    public Consumer(MenuHolder menuHolder) {
        this.menuHolder = menuHolder;
    }

    @Override
    public void run() {
        this.menuHolder.getMenu();
    }
}
