package com.murui.applet.exception;

import com.murui.applet.common.IErrorCode;
import lombok.Data;

/*
 * 自定义的异常类
 **/
@Data
public class ServiceException extends RuntimeException {
    private int code;

    public ServiceException(IErrorCode baseErrorCode){
        super(baseErrorCode.getMessage());
        this.code = baseErrorCode.getCode();
    }


}
