package com.taylor.weather;

import lombok.Data;

import java.util.Random;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/24 16:11
 */
@Data
public class Weather {
    public int tempreture;
    public int wet;
    public int wind;

    public synchronized void write() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        this.tempreture = random.nextInt(100);
        this.wet = random.nextInt(100);
        this.wind = random.nextInt(100);
    }


    public synchronized void read() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前温度：" + this.getTempreture());
        System.out.println("当前湿度：" + this.getWet());
        System.out.println("当前风速：" + this.getWind());
    }
}
