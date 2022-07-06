package com.murui.applet.entity.vo;

import com.murui.applet.entity.QuestionInfo.QuestionAndAnswer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 返回给前端查询答卷的数据
 */
@Data
public class AnswerVO {
    @ApiModelProperty(value = "问卷标题")
    private String paperTitle;

    @ApiModelProperty(value = "问卷说明")
    private String paperInfo;

    @ApiModelProperty(value = "问卷问题")
    private List<QuestionAndAnswer> questions;
}
