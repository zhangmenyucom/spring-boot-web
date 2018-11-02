package com.taylor.config;/**
 * ${author} on 2018/11/2.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/11/2 15:40
 */
@Configuration
public class Test {
    @Bean
    @ConfigurationProperties
    public TestBean getInstance() {
        return new TestBean();
    }
}
