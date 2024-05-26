/**
 * 功能：
 * 作者：Pjkang
 * 日期：2024/5/26 10:40
 */

package com.example.springboot.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())  // 配置JWT的拦截规则
                .addPathPatterns("/**")            // 拦截所有请求
                .excludePathPatterns("/login")      // 不拦截登录请求;
                .excludePathPatterns("/register");  // 不拦截注册请求
        super.addInterceptors(registry);
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

}