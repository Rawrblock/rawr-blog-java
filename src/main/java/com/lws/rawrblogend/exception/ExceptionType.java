package com.lws.rawrblogend.exception;

// 异常文本、异常代码枚举类
public enum ExceptionType {

    // 400：基础错误 404开头：null错误等
    INNER_ERROR(500, "系统内部错误"),
    UNAUTHORIZED(401, "未登录"),
    BAD_REQUEST(400, "请求错误"),
    FORBIDDEN(403, "无权操作"),
    NOT_FOUND(404, "未找到"),
    USER_EMAIL_DUPLICATE(40001002, "邮箱已重复"),
    USER_NOT_FOUND(40401001, "邮箱不存在"),
    USER_PASSWORD_NOT_MATCH(40001003, "邮箱或密码错误"),
    USER_NOT_ENABLED(50001001, "用户未启用"),
    USER_LOCKED(50001002, "用户被锁定"),
    USER_TOKEN_INVALID(50001003, "Token过期或失效,请重新登录"),
    FILE_NOT_FOUND(50002001, "文件不存在"),
    BLOG_NOT_FOUND(50003001, "该博客不存在");

    private final Integer code;

    private final String message;

    ExceptionType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
