package com.taylor.weather;

import lombok.Data;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();


    public void write() {
        try {
            writeLock.lock();
            Random random = new Random();
            this.tempreture = random.nextInt(100);
            this.wet = random.nextInt(100);
            this.wind = random.nextInt(100);
            System.out.println("写：当前温度：" + this.getTempreture());
            System.out.println("写：当前湿度：" + this.getWet());
            System.out.println("写：当前风速：" + this.getWind());
            System.out.println("************************************");
            System.out.println();
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }

    }

    public void read() {
        try {
            readLock.lock();
            System.out.println("读：当前温度：" + this.getTempreture());
            System.out.println("读：当前湿度：" + this.getWet());
            System.out.println("读：当前风速：" + this.getWind());
            System.out.println("************************************");
            System.out.println();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }
}
