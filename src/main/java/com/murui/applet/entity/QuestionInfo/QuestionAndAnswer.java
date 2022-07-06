package com.murui.applet.entity.QuestionInfo;

import com.murui.applet.entity.AnswerInfo.ReadAnswer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
/**
 * 返回给前端查询答卷请求的参数,用于AnswerVO
 */
@Data
public class QuestionAndAnswer {
    @ApiModelProperty(value = "问题id")
    private Long id;

    @ApiModelProperty(value = "问题标题")
    private String questionTitle;

    @ApiModelProperty(value = "辅助说明")
    private String questionInfo;

    @ApiModelProperty(value = "答题数据")
    private List<ReadAnswer> answers;
}
