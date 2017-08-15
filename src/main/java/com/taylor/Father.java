package com.taylor;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/8/2 15:33
 */
@Data
public class Father {
    private String name;

    static class FatherHolder {
        private static Father getFatherInstance() {
            return new Father("zhansan");
        }
    }

    private Father(String name) {
        this.name = name;
    }

    public static  Father getInstance() {
        System.out.println("我被调用啦");
        return FatherHolder.getFatherInstance();
    }
}
