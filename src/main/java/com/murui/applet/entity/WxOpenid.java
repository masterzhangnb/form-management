package com.murui.applet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("sys_openid")
public class WxOpenid {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "openid")
    private String openid;
}
