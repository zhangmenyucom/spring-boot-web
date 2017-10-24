package com.taylor.algo.printnum;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/24 16:50
 */
public class ThreadA extends Thread {
    private NumberPrinter numberPrinter;

    ThreadA(NumberPrinter numberPrinter, String name) {
        super(name);

        this.numberPrinter = numberPrinter;
    }

    @Override
    public void run() {
        numberPrinter.PrintA();
    }
}
