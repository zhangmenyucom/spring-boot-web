package com.taylor.thread;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/8/29 16:47
 */
@Data
@AllArgsConstructor
public class ThreadA extends Thread {
    private ObjectA objectA;

    @Override
    public void run() {
        synchronized (ObjectA.class) {
            while (true) {
                objectA.add();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
