package com.taylor;

import java.util.concurrent.Callable;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/8/2 17:39
 */
public class ThreadA implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "123";
    }

    public static void main(String... args) {
        try {
            new ThreadA().call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
