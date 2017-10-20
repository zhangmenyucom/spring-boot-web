package com.taylor.builder;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/19 17:08
 */
@Data
public abstract class CarModelTemplate {
    private String carName;

    abstract void start();

    abstract void stop();

    abstract boolean alarm();

    abstract void boom();

    public void run() {
        start();
        if (alarm()) {
            boom();
        }
        stop();
    }


}
