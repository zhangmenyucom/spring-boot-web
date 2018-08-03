package com.taylor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 *
 * @author taylor
 */
@SpringBootApplication
@EnableScheduling
public class StockApplicationStarter {
    public static void main(String... args) {
        SpringApplication.run(StockApplicationStarter.class, args);
    }
}
