package com.internship.common_web.interceptor;

import com.internship.common.utils.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1. 从网关透传的 Header 中拿到 ID
        String userId = request.getHeader("X-User-Id");

        // 2. 存入公共的柜子
        if (StringUtils.hasText(userId)) {
            UserContext.setUserId(Long.valueOf(userId));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 3. 任务完成，清理柜子
        UserContext.removeUserId();
    }
}