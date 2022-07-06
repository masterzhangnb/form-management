package com.murui.applet.entity.vo;

import com.murui.applet.entity.QuestionInfo.QuestionAndOption;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
/**
 * 返回给前端查询问卷请求的参数
 */
@Data
public class PaperVO {

    @ApiModelProperty(value = "问卷id")
    private Long id;

    @ApiModelProperty(value = "问卷标题")
    private String paperTitle;

    @ApiModelProperty(value = "问卷说明")
    private String paperInfo;

    @ApiModelProperty(value = "问卷问题及选项")
    private List<QuestionAndOption> questions;
}
