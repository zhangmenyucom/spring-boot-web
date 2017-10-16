package com.taylor;

public class MyThread implements Runnable {
    private int count = 1;
    private int number;

    public MyThread(int num) {
        number = num;
        System.out.println("Create Thread-" + number);
    }

    @Override
    public void run() {
        System.out.println("");
        /*while (true) {
            System.out.println("Thread-" + number + " run " + count + " time(s)");
            if (count == 3) {
                return;
            }
            count++;
        }*/
    }
}