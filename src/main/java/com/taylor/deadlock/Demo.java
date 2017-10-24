package com.taylor.deadlock;

import com.taylor.thread.ObjectA;
import javafx.beans.binding.ObjectBinding;

import java.nio.file.FileSystemNotFoundException;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/24 9:57
 */
public class Demo {
    public static void main(String[] args) {
        Object objectA = new Object();
        Object objectB = new Object();
        Object objectC = new Object();
        ThreadT thread1 = new ThreadT(objectA, objectC, "thread1");
        ThreadT thread2 = new ThreadT(objectB, objectC, "thread2");
        thread1.start();
        thread2.start();
    }
}

class ThreadT extends Thread {
    private final Object objectA;
    private final Object objectB;

    public ThreadT(Object objectA, Object objectB, String name) {
        super(name);
        this.objectA = objectA;
        this.objectB = objectB;
    }

    @Override
    public void run() {
        synchronized (objectA) {
            try {
                System.out.println(Thread.currentThread().getName() + ":first layer is executed");
                Thread.sleep(5000);
                synchronized (objectB) {
                    System.out.println(Thread.currentThread().getName() + ":second layer is executed");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
