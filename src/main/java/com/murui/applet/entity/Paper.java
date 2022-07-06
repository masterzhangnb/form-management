package com.murui.applet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@TableName("sys_paper") // 问卷表
public class Paper {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "问卷标题")
    private String paperTitle;

    @ApiModelProperty(value = "问卷说明")
    private String paperInfo;

    @ApiModelProperty(value = "问卷状态")
    private Integer status;

    public Paper(Long userId, String paperTitle, String paperInfo, Integer status) {
        this.userId = userId;
        this.paperTitle = paperTitle;
        this.paperInfo = paperInfo;
        this.status = status;
    }

}
