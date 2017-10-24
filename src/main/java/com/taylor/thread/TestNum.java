package com.taylor.thread;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author taylor
 */
public class TestNum {
    /**
     * ①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
     **/
    private ThreadLocal<Number> seqNum = new ThreadLocal<>();


    /**
     * ②获取下一个序列值
     **/
    public Number getNextNum() {
        try {
            seqNum.get().setA(seqNum.get().getA() + 1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            seqNum.remove();
        }
        return seqNum.get();
    }

    public TestNum() {
        Number number = new Number(1, "z");
        this.seqNum.set(number);

    }

    public static void main(String... args) {


        TestNum sn = new TestNum();

        // ③ 3个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();
    }

    private static class TestClient extends Thread {
        private TestNum sn;

        public TestClient(TestNum sn) {
            this.sn = sn;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                // ④每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn[" + sn.seqNum.get() + "]");
            }
        }
    }

    @Data
    @AllArgsConstructor
    static class Number {
        private int a;
        private String name;
    }
}
