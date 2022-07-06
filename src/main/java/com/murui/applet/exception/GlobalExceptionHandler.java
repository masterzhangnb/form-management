package com.murui.applet.exception;

import com.murui.applet.common.PlainResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 如果抛出的的是ServiceException，则调用该方法
     *
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 10:27 AM 6/20/2022
     * @param se 业务异常
     * @return com.murui.applet.common.PlainResult
    **/
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public PlainResult handle(ServiceException se){
        return PlainResult.errorResult(se.getCode(), se.getMessage());
    }
}
