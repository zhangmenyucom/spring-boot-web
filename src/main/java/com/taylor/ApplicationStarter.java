package com.taylor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 启动类
 */
@SpringBootApplication
public class ApplicationStarter {
    public static void main(String... args) {
        SpringApplication.run(ApplicationStarter.class, args);
        List<byte[]> listAll = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            final byte[] bytes = new byte[100];
            System.gc();
        }
        //List<String> list = new ArrayList<>(Integer.MAX_VALUE);
    }
}
