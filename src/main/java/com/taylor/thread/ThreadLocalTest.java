package com.taylor.thread;

import lombok.Data;

public class ThreadLocalTest {
    public static void main(String... args) {
        Account account = new Account("初始名");
        new MyTest(account, "线程甲").start();
        new MyTest(account, "线程乙").start();

    }
}

@Data
class Account {
    private ThreadLocal<String> name = new ThreadLocal<>();

    public String getName() {
        return name.get();
    }

    public void setName(String str) {
        this.name.set(str);
    }

    public Account(String str) {
        this.name.set(str);
        System.out.println("---" + this.name.get());
    }
}


class MyTest extends Thread {

    private Account account;

    public MyTest(Account account, String name) {
        super(name);
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i == 6) {
                account.setName(getName());
            }
            System.out.println(account.getName() + "账户i的值为:" + i);
        }
    }

}