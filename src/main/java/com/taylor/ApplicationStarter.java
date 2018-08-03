package com.taylor;

import com.taylor.dto.TestDto;
import com.taylor.dto.TestMapper;
import com.taylor.entity.TestEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
public class ApplicationStarter {
    public static void main(String... args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }
}
