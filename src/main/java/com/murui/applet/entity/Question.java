package com.murui.applet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("sys_question") // 单选或者多选题的选项表
public class Question {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "问卷id")
    private Long paperId;

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

    public Question(Long paperId, String questionTitle, String questionInfo, Integer questionType, Boolean isRequired, Integer minLimit, Integer maxLimit, Long sizeLimit, Integer fileNum, String fileType, String showQuestion, Long nextQuestion) {
        this.paperId = paperId;
        this.questionTitle = questionTitle;
        this.questionInfo = questionInfo;
        this.questionType = questionType;
        this.isRequired = isRequired;
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
        this.sizeLimit = sizeLimit;
        this.fileNum = fileNum;
        this.fileType = fileType;
        this.showQuestion = showQuestion;
        this.nextQuestion = nextQuestion;
    }
}
