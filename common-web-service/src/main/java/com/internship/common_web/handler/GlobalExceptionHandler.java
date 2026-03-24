package com.internship.common_web.handler;


import com.internship.common.exception.BizException;
import com.internship.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // 核心注解：拦截全站所有的 RestController 异常
public class GlobalExceptionHandler {

    /**
     * 专门拦截咱们自定义的【业务异常】
     */
    @ExceptionHandler(BizException.class)
    public Result<?> handleBizException(BizException e) {
        log.error("业务异常告警：{}", e.getMessage());
        return Result.build(e.getResultCodeEnum());
    }

    /**
     * 拦截【系统级异常】（比如数据库断了、代码写错空指针了）
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        log.error("系统崩溃级异常：", e);
        return Result.error("服务器开小差了，请稍后再试");
    }
}