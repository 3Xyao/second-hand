package com.internship.common_web.config;

import com.internship.common_web.interceptor.UserInfoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CommonWebAutoConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 让搬运工上岗，拦截所有请求
        registry.addInterceptor(new UserInfoInterceptor()).addPathPatterns("/**");
    }
}
