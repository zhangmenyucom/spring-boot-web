package com.taylor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/16 15:23
 */
public class Child extends Father{
    public void doSomething(HashMap map){
        System.out.println("this is child speaking");
    }

    public static void main(String[] args) {
        Father c=new Child();
        c.doSomething(null);
    }
}
