package com.taylor.weather;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/24 16:13
 */
@Data
public class Producer extends Thread {
    private Weather weather;

    public Producer(Weather weather, String name) {
        super(name);
        this.weather = weather;
    }

    @Override
    public void run() {
        while (true) {
            weather.write();
        }
    }
}
