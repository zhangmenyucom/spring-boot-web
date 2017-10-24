package com.taylor.readwritelcok;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/24 10:24
 */
public class Demo {
    public static void main(String[] args) {
        ObjectO o = new ObjectO();
        ThreadT t1 = new ThreadT(o, "t1");
        ThreadT t2 = new ThreadT(o, "t2");
        ThreadT t3 = new ThreadT(o, "t3");
        ThreadT t4 = new ThreadT(o, "t4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class ThreadT extends Thread {
    private ObjectO object;

    ThreadT(ObjectO object, String name) {
        super(name);
        this.object = object;
    }


    @Override
    public void run() {
        object.read();
        object.write();
    }
}

class ObjectO {
    private Lock readLock = new ReentrantReadWriteLock().readLock();
    private Lock writeLock = new ReentrantReadWriteLock().writeLock();

    public void read() {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + ":I am coming in--reading");
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public void write() {
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + ":I am coming in--writing");
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }
}

