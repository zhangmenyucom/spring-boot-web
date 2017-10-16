package com.taylor;

import java.util.concurrent.Callable;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/9/15 15:06
 */
public class ThreadTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(4000);
        return "Task" + Thread.currentThread().getName();
    }
}
