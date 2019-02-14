package com.taylor.config;

import com.taylor.component.AuthorizationInterceptor;
import com.taylor.component.LoginUserHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author Taylor
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginUserHandlerMethodArgumentResolver userArgumentResolver;

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    /**
     * 配置ArgumentResolvers
     * 实现采用业务逻辑，向controllor 方法中注入参数
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }

    /**
     * @desc授权拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/api/**");
    }
}
