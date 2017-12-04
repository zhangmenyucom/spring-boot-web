package com.taylor.thread.oldadd;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/27 17:45
 */
public class Demo {
    public static void main(String[] args) {
        Thread oodThread=new PrintOddThread("打印偶数",1000);
        Thread addThread=new PrintAddThread("打印奇数",1000);
        oodThread.start();
        addThread.start();
    }
}

class PrintOddThread extends Thread {
    private int number;

    PrintOddThread(String name, int number) {
        super(name);
        this.number = number;
    }

    @Override
    public void run() {
        for (int i =0; i <= number; i++) {
            if(i%2==0){
                System.out.println(Thread.currentThread().getName()+":"+i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}


class PrintAddThread extends Thread {
    private int number;

    PrintAddThread(String name, int number) {
        super(name);
        this.number = number;
    }

    @Override
    public void run() {
        for (int i =0; i <= number; i++) {
            if(i%2!=0){
                System.out.println(Thread.currentThread().getName()+":"+i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}