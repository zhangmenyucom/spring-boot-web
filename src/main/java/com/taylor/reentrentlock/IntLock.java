package com.taylor.reentrentlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/4 16:38
 */
public class IntLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public IntLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("lock1.isinterupted");
                }
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("lock2 is interrupted");
                }
                lock1.lockInterruptibly();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getName() + "线程退出");

        }

    }

    public static void main(String[] args) throws InterruptedException {
        IntLock r1=new IntLock(1);
        IntLock r2=new IntLock(2);
        Thread t1=new Thread(r1,"t1");
        Thread t2=new Thread(r2,"t2");
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t2.interrupt();
    }
}
