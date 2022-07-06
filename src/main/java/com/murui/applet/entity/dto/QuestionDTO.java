package com.murui.applet.entity.dto;

import com.murui.applet.entity.Option;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 接收前端请求新增问题和选项的参数
 */
@Data
public class QuestionDTO {
    @ApiModelProperty(value = "问题id")
    private Long id;

    @ApiModelProperty(value = "问卷id")
    private String paperId;

    @ApiModelProperty(value = "问题标题")
    private String questionTitle;

    @ApiModelProperty(value = "辅助说明")
    private String questionInfo;

    @ApiModelProperty(value = "问题类型")
    private Integer questionType;

    @ApiModelProperty(value = "是否必答")
    private Boolean isRequired;

    @ApiModelProperty(value = "最少选择")
    private Integer minLimit;

    @ApiModelProperty(value = "最多选择")
    private Integer maxLimit;

    @ApiModelProperty(value = "文件大小限制")
    private Long sizeLimit;

    @ApiModelProperty(value = "文件数量限制")
    private Integer fileNum;

    @ApiModelProperty(value = "文件类型")
    private String fileType;

    @ApiModelProperty(value = "显示条件")
    private String showQuestion;

    @ApiModelProperty(value = "跳题设置")
    private Long nextQuestion;

    @ApiModelProperty(value = "问题选项")
    private List<Option> options;
}
