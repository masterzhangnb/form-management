package com.murui.applet.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * 接收前端请求的查询问卷数据
 */
@Data
public class PaperBasicDTO {

    @ApiModelProperty(value = "问卷id（主键）")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "问卷标题")
    private String paperTitle;

    @ApiModelProperty(value = "问卷状态")
    @Range(min = -1,max = 2)
    private String status;

}