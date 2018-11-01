package com.taylor.lock;

public  class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public synchronized void run() {
        service.seckill();
    }
}

class Test {
    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 200; i++) {
            ThreadA threadA = new ThreadA(service);
            threadA.start();
        }
    }
}