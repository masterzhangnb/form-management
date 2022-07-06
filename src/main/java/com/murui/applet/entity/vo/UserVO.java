package com.murui.applet.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 返回给前端登录请求的参数
 */
@Data
public class UserVO {

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    private String token;
}
