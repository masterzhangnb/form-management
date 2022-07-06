package com.murui.applet.controller;

import com.murui.applet.common.PlainResult;
import com.murui.applet.entity.Option;
import com.murui.applet.entity.Question;
import com.murui.applet.entity.dto.QuestionDTO;
import com.murui.applet.service.IQuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName QuestionController
 * @Description 问题表
 * @Author masterzhang && masterzhangnb@gmail.com
 * @Date 6/23/2022 9:36 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Resource
    public IQuestionService questionService;

    /**
     * 查询 sys_question 表的所有记录
     *
     * @return com.murui.applet.common.PlainResult<java.util.List < com.murui.applet.entity.Question>>
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 11:31 AM 6/27/2022
     **/
    @GetMapping("/list")
    public PlainResult<List<Question>> listQuestion() {
        return PlainResult.successResult(questionService.list());
    }


    /**
     * 新增或修改表 sys_question 和 sys_option
     *
     * @param questionDTO
     * @return com.murui.applet.common.PlainResult<java.lang.Boolean>
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 11:44 AM 6/27/2022
     **/
    @PostMapping("/saveOrUpdate")
    public PlainResult<Boolean> saveOrUpdate(@RequestBody QuestionDTO questionDTO) {
        Boolean questionAndOption = questionService.saveQuestionAndOption(questionDTO);
        return PlainResult.successResult(questionAndOption);
    }

}
