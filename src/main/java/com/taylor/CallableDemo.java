package com.taylor;

import java.util.concurrent.*;

public class CallableDemo implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("haha this is running ");
        Thread.sleep(9000);
        return "test";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableDemo callableDemo = new CallableDemo();
        FutureTask<String> callableDemoFutureTask = new FutureTask<>(callableDemo);
        ExecutorService executorService= Executors.newFixedThreadPool(1);
        executorService.submit(callableDemoFutureTask);
        if("test".equals(callableDemoFutureTask.get())){
            System.out.println("has run alread");
        }
    }
}