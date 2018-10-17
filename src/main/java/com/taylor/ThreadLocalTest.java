package com.taylor;

public class ThreadLocalTest {
    ThreadLocal<Long> longLocal = new ThreadLocal<>();
    ThreadLocal<Cat> stringLocal = new ThreadLocal<>();

    public void set(Cat cat) {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(cat);
    }

    public long getLong() {
        return longLocal.get();
    }

    public Cat getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest test = new ThreadLocalTest();
        Cat cat=new Cat("zhangsan");
        test.set(cat);
        System.out.println(test.getLong());
        System.out.println(test.getString().getName());
        // 在这里新建了一个线程
        Thread thread1 = new Thread(() -> {
            // 当这里调用了set方法，进一步调用了ThreadLocal的set方法是，会将ThreadLocal变量存储到该线程的ThreadLocalMap类型的成员变量threadLocals中，注意的是这个threadLocals变量是Thread线程的一个变量，而不是ThreadLocal类的变量。
            test.set(cat);
            test.getString().setName("lisi");
            System.out.println(test.getLong());
            System.out.println(test.getString().getName());
        });
        thread1.start();
        thread1.join();

        System.out.println(test.getLong());
        System.out.println(test.getString().getName());
    }


}