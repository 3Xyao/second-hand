package com.internship.common.result;

import lombok.Getter;

/**
 * 全局统一返回状态码枚举
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "操作成功"),
    FAIL(500, "系统异常，请稍后再试"),

    // 预留的安保暗号（以后网关和 Auth 会用到）
    UNAUTHORIZED(401, "尚未登录或Token已过期"),
    FORBIDDEN(403, "权限不足，禁止访问"),

    // 业务参数暗号
    PARAM_ERROR(400, "参数校验失败");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}