package com.taylor.singleton;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/20 17:11
 */
public class Singleton {
    private int id;
    private String name;
    private static volatile Singleton singleton;

    private Singleton() {
    }

    /**
     * doulecheck
     **/
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    private static class SingletonHolder {
        private static Singleton singleton = new Singleton();
    }

    /**
     * static inner class
     **/
    public Singleton getSingleton() {
        return SingletonHolder.singleton;
    }
}
