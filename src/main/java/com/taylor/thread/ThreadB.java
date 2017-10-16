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
public class ThreadB extends Thread {
    private ObjectA objectA;

    @Override
    public void run() {
        while(true) {
            objectA.del();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
