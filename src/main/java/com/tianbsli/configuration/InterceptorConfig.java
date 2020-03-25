package com.tianbsli.configuration;

import com.tianbsli.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Jorsh
 * @version 1.0
 * @date 2020/3/25 11:35 上午
 *
 * 拦截所有请求
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册LogInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new LogInterceptor());
        //拦截制定请求（/**为所有请求）
        registration.addPathPatterns(
                "/**"
        );
    }
}

