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
        Thread t1 = new ThreadRead(o, "t1");
        Thread t2 = new ThreadRead(o, "t2");
        Thread t3 = new ThreadWrite(o, "t3");
        Thread t4 = new ThreadWrite(o, "t4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class ThreadRead extends Thread {
    private ObjectO object;

    ThreadRead(ObjectO object, String name) {
        super(name);
        this.object = object;
    }


    @Override
    public void run() {
        while (true) {
            object.read();
        }
    }
}

class ThreadWrite extends Thread {
    private ObjectO object;

    ThreadWrite(ObjectO object, String name) {
        super(name);
        this.object = object;
    }

    @Override
    public void run() {
        while (true) {
            object.write();
        }
    }
}

class ObjectO {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public void read() {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + ":I am coming in--reading");
            Thread.sleep(100);
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
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }
}

