package com.murui.applet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("sys_option") // 问题选项表
public class Option {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "问题id")
    private Long questionId;

    @ApiModelProperty(value = "选项内容")
    private String content;

    @ApiModelProperty(value = "按选项跳题")
    private Long gotoQuestion;

    @ApiModelProperty(value = "是否删除")
    private Boolean isDeleted;


}
