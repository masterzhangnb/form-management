package com.murui.applet.entity.AnswerInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回答题数据
 */
@Data
public class ReadAnswer {
    @ApiModelProperty(value = "答题id")
    private Long id;

    @ApiModelProperty(value = "选项id")
    private Long optionId;

    @ApiModelProperty(value = "选项内容")
    private String content;

    @ApiModelProperty(value = "回答内容")
    private String answerContent;

}
