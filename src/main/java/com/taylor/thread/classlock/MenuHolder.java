package com.taylor.thread.classlock;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/20 17:43
 */
public class MenuHolder {
    public static int menu = 1000;

    public void getMenu() {
        synchronized (MenuHolder.class) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            menu--;
            System.out.println(Thread.currentThread().getName() + "menuNumberLeft:" + menu);
        }
    }

    public static void main(String... args) {
        for (int i = 0; i < 1000; i++) {
            Consumer consumer = new Consumer(new MenuHolder());
            consumer.start();
        }
    }
}
