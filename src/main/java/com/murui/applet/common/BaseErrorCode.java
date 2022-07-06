package com.murui.applet.common;

import lombok.Getter;

@Getter
public enum BaseErrorCode implements IErrorCode{
    BAD_REQUEST_CODE(400, "错误请求"),
    UNKNOWN_ERROR(500, "服务器发生未知错误"),
    USER_NOT_EXIST(131001, "用户账户不存在"),
    ACCOUNT_OR_PASSWORD_ERROR(131002,"用户账号或密码错误"),
    NO_TOKEN(131003, "无token，请重新登录"),
    TOKEN_ERROR(131004, "token验证失败，请重新登录"),
    USER_ACCOUNT_BLOCKED(131005, "用户账号被封禁"),
    USER_DID_NOT_PRIVACY_AGREEMENT(131006, "用户未同意隐私协议"),
    USER_NO_PRIVILEGE(131007, "用户无权访问他人资源"),

    /**
     * todo 该code码值如果修改请务必通知前端
     * 无权访问该服务
     */
    ACCESS_NO_PRIVILEGE(131008, "无权访问该服务"),
    ACCESS_RESOURCE_NOT_EXIST(131009, "访问资源不存在"),
    OPENID_NOT_EXIST(131010, "没有获取到OPENID"),
    ACCOUNT_OR_PASSWORD_ISNULL(1310011,"账号或密码不能为空"),
    OSS_UPLOAD_FAIL(1310012,"OSS文件上传失败！")
    ;

    private final int code;
    private final String message;

    BaseErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
