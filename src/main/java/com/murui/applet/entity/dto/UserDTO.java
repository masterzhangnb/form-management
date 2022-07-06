package com.murui.applet.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 接收前端登录请求的参数
 */
@Data
public class UserDTO {

    @ApiModelProperty(value = "用户名")
    @NotBlank
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank
    private String password;

}
