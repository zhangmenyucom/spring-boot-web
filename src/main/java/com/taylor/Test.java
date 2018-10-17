package com.taylor;/**
 * ${author} on 2018/10/17.
 */

import lombok.Data;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/10/17 15:33
 */
public class Test extends Thread{
    public static ThreadLocal<Cat> catThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        Test t1=new Test();
        Cat cat=new Cat("miao");
        catThreadLocal.set(cat);
    }
}
