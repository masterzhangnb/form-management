package com.murui.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.murui.applet.entity.Question;
import com.murui.applet.entity.dto.QuestionDTO;

public interface IQuestionService extends IService<Question> {

    Boolean saveQuestionAndOption(QuestionDTO questionDTO);
}
