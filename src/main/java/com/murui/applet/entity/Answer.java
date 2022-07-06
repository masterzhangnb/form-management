package com.murui.applet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_answer") // 答题表
public class Answer {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "问卷id")
    private Long paperId;

    @ApiModelProperty(value = "问题id")
    private Long questionId;

    @ApiModelProperty(value = "选项id")
    private Long optionId;

    @ApiModelProperty(value = "答题内容")
    private String answerContent;

    @ApiModelProperty(value = "是否删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "提交时间")
    private Date createTime;
}
