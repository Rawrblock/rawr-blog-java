package com.lws.rawrblogend.exception;

// 异常报错处理
// 业务异常类 继承
public class BizException extends RuntimeException {
    // 定义业务代码常量
    private final Integer code;

    //
    public BizException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.code = exceptionType.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
