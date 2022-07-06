package com.murui.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.murui.applet.entity.Answer;
import com.murui.applet.entity.Paper;
import com.murui.applet.entity.dto.AnswerDTO;
import com.murui.applet.entity.vo.AnswerVO;

import java.util.List;

public interface IAnswerService extends IService<Answer> {

    List<Answer> toAnswers(List<AnswerDTO> answerDTOList);

    AnswerVO getAnswerDetail(Paper paper,Long userId);
}
