package com.taylor.thread;

import lombok.Data;
import sun.applet.Main;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/8/29 16:48
 */
@Data
public class ObjectA {
    public int a;

    public synchronized void add() {
        System.out.println("i am adding ");
    }

    public synchronized void del() {
        System.out.println("i am deling ");
    }

    public static void main(String... args) {
        ObjectA objectA = new ObjectA();
        ThreadA threadA = new ThreadA(objectA);
        ThreadB threadB = new ThreadB(objectA);
        threadA.start();
        threadB.start();
    }
}
