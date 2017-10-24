package com.taylor.algo.printnum;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/24 16:39
 */
public class NumberPrinter {
    private ReentrantLock lock = new ReentrantLock();
    private Condition conditonA = lock.newCondition();
    private Condition conditonB = lock.newCondition();
    private Condition conditonC = lock.newCondition();
    private volatile int count;

    NumberPrinter(int count) {
        this.count = count;
    }

    private int flag = 1;

    public void PrintA() {
        try {
            lock.lock();
            while (true) {
                while (flag != 1) {
                    conditonA.await();
                }
                for (int i = 0; i < 5; i++) {
                    count++;
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + ":" + count);
                }
                flag = 2;
                conditonB.signal();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public void PrintB() {
        try {
            lock.lock();
            while (true) {
                while (flag != 2) {
                    conditonB.await();
                }
                for (int i = 0; i < 5; i++) {
                    count++;
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + ":" + count);
                }
                flag = 3;
                conditonC.signal();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public void PrintC() {
        try {
            lock.lock();
            while (true) {
                while (flag != 3) {
                    conditonC.await();
                }
                for (int i = 0; i < 5; i++) {
                    count++;
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + ":" + count);
                }
                flag = 1;
                conditonA.signal();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

}
