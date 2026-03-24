package com.internship.common.utils;

public class UserContext {
    // 使用 ThreadLocal 存储用户 ID
    private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        THREAD_LOCAL.set(userId);
    }

    public static Long getUserId() {
        return THREAD_LOCAL.get();
    }

    /**
     * ⚠️ 极其重要：请求结束必须清理，否则在线程池环境下会造成内存泄漏或数据错乱
     */
    public static void removeUserId() {
        THREAD_LOCAL.remove();
    }
}