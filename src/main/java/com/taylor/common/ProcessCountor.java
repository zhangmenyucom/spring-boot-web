package com.taylor.common;


import org.springframework.util.StopWatch;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaolu.zhang
 * @desc:计时器
 * @date: 2018/1/6 12:08
 */
public class ProcessCountor extends Thread {
    public static AtomicInteger CURRENT = new AtomicInteger(0);

    public static AtomicInteger  TOTAL = new AtomicInteger(3448);

    public static AtomicInteger  FAIL_COUNT = new AtomicInteger(0);

    @Override
    public void run() {
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        DecimalFormat df   = new DecimalFormat("######0.00");
        while (CURRENT.intValue() < (TOTAL.get()-FAIL_COUNT.get())) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("处理进度："+df.format(CURRENT.intValue()*100/(TOTAL.get()-FAIL_COUNT.get()))+"%"+"当前："+CURRENT.intValue()+"总："+(TOTAL.get()-FAIL_COUNT.get()));
        }
        stopWatch.stop();
        System.out.println("处理完毕，总用时："+(int)stopWatch.getTotalTimeSeconds()/60+"分钟"+(int)stopWatch.getTotalTimeSeconds()%60+"秒");
    }
}

