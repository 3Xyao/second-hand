package com.internship.common.result;

import lombok.Data;

/**
 * 全局统一返回结果类
 * @param <T> 具体的数据类型
 */
@Data
public class Result<T> {

    // 状态码 (比如 200)
    private Integer code;

    // 提示信息 (比如 "登录成功")
    private String message;

    // 真正的数据包裹 (比如 LoginVO)
    private T data;

    // 私有化构造方法，强制大家使用静态方法，代码更优雅
    private Result() {}

    /**
     * 内部构建方法
     */
    protected static <T> Result<T> build(T data, Integer code, String message) {
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 专门供全局异常处理器或手动报错使用的静态方法
     * @param codeEnum 错误码枚举
     */
    public static <T> Result<T> build(ResultCodeEnum codeEnum) {
        // 内部依然调用那个受保护的 build，但对外它是 public 的
        return build(null, codeEnum.getCode(), codeEnum.getMessage());
    }

    // ==========================================
    // 👑 成功响应的快捷方法
    // ==========================================

    // 成功（不带数据，只说成功）
    public static <T> Result<T> success() {
        return build(null, ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
    }

    // 成功（带数据返回，比如返回 LoginVO）
    public static <T> Result<T> success(T data) {
        return build(data, ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
    }

    // ==========================================
    // 🚨 失败响应的快捷方法
    // ==========================================

    // 失败（用默认的 500 异常）
    public static <T> Result<T> error() {
        return build(null, ResultCodeEnum.FAIL.getCode(), ResultCodeEnum.FAIL.getMessage());
    }

    // 失败（自定义失败提示，比如 "密码错误"）
    public static <T> Result<T> error(String message) {
        return build(null, ResultCodeEnum.FAIL.getCode(), message);
    }

    // 失败（传入指定的枚举状态，比如 401 没登录）
    public static <T> Result<T> error(ResultCodeEnum resultCodeEnum) {
        return build(null, resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }

    // ==========================================
    // 🔍 辅助判断方法（给内部微服务用的）
    // ==========================================
    public boolean isSuccess() {
        return this.code.equals(ResultCodeEnum.SUCCESS.getCode());
    }
}