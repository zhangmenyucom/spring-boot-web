package com.taylor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadPool {
    public static void main(String... args) {
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(3);
        exec.scheduleAtFixedRate(new MyThread(1), 3, 10, TimeUnit.SECONDS);
       // exec.shutdown();
    }
}