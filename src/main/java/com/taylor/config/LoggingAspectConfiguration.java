package com.taylor.config;

import com.taylor.aop.log.LogInfoAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

/**
 * @author Taylor
 */
@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {
    @Bean
    @Profile("dev")
    public LogInfoAspect logInfoAspectInstance(Environment env) {
        return new LogInfoAspect(env);
    }
}
