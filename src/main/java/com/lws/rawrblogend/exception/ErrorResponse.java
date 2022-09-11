package com.lws.rawrblogend.exception;

import lombok.Data;

// 错误响应头类
@Data
public class ErrorResponse {

    private Integer code;

    private String message;

    private Object trace;

}
