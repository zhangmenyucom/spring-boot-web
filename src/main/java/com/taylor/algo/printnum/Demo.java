package com.taylor.algo.printnum;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/24 16:50
 */
public class Demo {
    public static void main(String[] args) {
        NumberPrinter numberPrinter = new NumberPrinter(0);
        Thread threadA = new ThreadA(numberPrinter, "ThreadA");
        Thread threadB = new ThreadB(numberPrinter, "ThreadB");
        Thread threadC = new ThreadC(numberPrinter, "ThreadC");
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
