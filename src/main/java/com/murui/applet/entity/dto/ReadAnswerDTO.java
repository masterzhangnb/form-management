package com.murui.applet.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接收前端请求的查看指定答卷参数
 */
@Data
public class ReadAnswerDTO {
    @ApiModelProperty(value = "答卷id")
    private Long paperId;

    @ApiModelProperty(value = "用户id")
    private Long userId;
}
