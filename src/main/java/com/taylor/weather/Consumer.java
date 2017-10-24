package com.taylor.weather;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/24 16:19
 */
public class Consumer extends Thread {
    private Weather weather;

    public Consumer(Weather weather, String name) {
        super(name);
        this.weather = weather;
    }

    @Override
    public void run() {
        while (true) {
            weather.read();
        }
    }

    public static void main(String[] args) {
        Weather weather = new Weather();
        Producer p = new Producer(weather, "producer");
        Consumer c = new Consumer(weather, "consumer");
        p.start();
        c.start();
    }
}
