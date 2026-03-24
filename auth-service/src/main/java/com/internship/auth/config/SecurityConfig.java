package com.internship.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    // 1. 注册大名鼎鼎的 BCrypt 密码加密器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. 现代版 Spring Security 配置 (放行 /auth/login)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. 关闭 CSRF (新版推荐使用 Lambda 写法)
                .csrf(AbstractHttpConfigurer::disable)

                // 2. 开启跨域允许 (二手平台前后端分离必备)
                .cors(Customizer.withDefaults())

                // 3. 设置 Session 为无状态 (咱们用 JWT，不需要 Session)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 4. 配置权限拦截规则
                .authorizeHttpRequests(auth -> auth
                        // 放行登录、注册接口
                        .requestMatchers("/auth/login", "/auth/register").permitAll()
                        // 放行 Swagger 接口文档 (咱们刚配好的)
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // 其他所有请求都需要认证
                        .anyRequest().authenticated()
                );
        // 如果你还没写 JWT 过滤器，先注释掉这行，否则会因为没有认证而一直 403
        // http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
