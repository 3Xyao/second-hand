package com.internship.common.exception;

import com.internship.common.result.ResultCodeEnum;
import lombok.Getter;

/**
 * 自定义业务异常：专门用来在 Service 层“甩锅”
 */
@Getter
public class BizException extends RuntimeException {

    private final ResultCodeEnum resultCodeEnum;

    public BizException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.resultCodeEnum = resultCodeEnum;
    }

    public BizException(String message) {
        super(message);
        this.resultCodeEnum = ResultCodeEnum.FAIL; // 默认 500
    }
}