package com.taylor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 启动类
 * @author Taylor
 */
@SpringBootApplication
public class ApplicationStarter{
    public static void main(String... args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }
}
