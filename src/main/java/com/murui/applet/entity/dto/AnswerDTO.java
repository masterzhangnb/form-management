package com.murui.applet.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 接收前端请求的提交答卷参数
 */
@Data
public class AnswerDTO {

    @ApiModelProperty(value = "答题id")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "问卷id")
    private Long paperId;

    @ApiModelProperty(value = "问题id")
    private Long questionId;

    @ApiModelProperty(value = "选择题选项ids")
    private List<Long> optionIds;

    @ApiModelProperty(value = "答题内容")
    private String answerContent;

    @ApiModelProperty(value = "是否删除")
    private Boolean isDeleted;
}
