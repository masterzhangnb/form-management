package com.murui.applet.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlainResult<T> {
    private boolean success;
    private int code;
    private String message;
    private T data;

    public static <T> PlainResult<T> successResult(T data) {
        PlainResult<T> commonResult = new PlainResult<>();
        commonResult.setSuccess(true);
        commonResult.setCode(0);
        commonResult.setMessage("success");
        commonResult.setData(data);
        return commonResult;
    }

    public static <T> PlainResult<T> successResult(String message, T data) {
        PlainResult<T> commonResult = new PlainResult<>();
        commonResult.setSuccess(true);
        commonResult.setCode(0);
        commonResult.setMessage(message);
        commonResult.setData(data);
        return commonResult;
    }

    public static <T> PlainResult<T> successResult(String message) {
        PlainResult<T> commonResult = new PlainResult<>();
        commonResult.setSuccess(true);
        commonResult.setCode(0);
        commonResult.setMessage(message);
        commonResult.setData(null);
        return commonResult;
    }

    public static <T> PlainResult<T> errorResult(IErrorCode baseErrorCode) {
        PlainResult<T> commonResult = new PlainResult<>();
        commonResult.setSuccess(false);
        commonResult.setCode(baseErrorCode.getCode());
        commonResult.setMessage(baseErrorCode.getMessage());
        commonResult.setData(null);
        return commonResult;
    }

    public static <T> PlainResult<T> errorResult(int code, String message) {
        PlainResult<T> commonResult = new PlainResult<>();
        commonResult.setSuccess(false);
        commonResult.setCode(code);
        commonResult.setMessage(message);
        commonResult.setData(null);
        return commonResult;
    }
}
