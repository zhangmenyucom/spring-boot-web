package com.taylor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * 启动类
 */
@SpringBootApplication
@EnableJms
public class ApplicationStarter {
    public static void main(String... args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }
}
