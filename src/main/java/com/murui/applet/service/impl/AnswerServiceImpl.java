package com.murui.applet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.murui.applet.entity.Answer;
import com.murui.applet.entity.AnswerInfo.ReadAnswer;
import com.murui.applet.entity.Option;
import com.murui.applet.entity.Paper;
import com.murui.applet.entity.Question;
import com.murui.applet.entity.QuestionInfo.QuestionAndAnswer;
import com.murui.applet.entity.dto.AnswerDTO;
import com.murui.applet.entity.vo.AnswerVO;
import com.murui.applet.mapper.AnswerMapper;
import com.murui.applet.service.IAnswerService;
import com.murui.applet.service.IOptionService;
import com.murui.applet.service.IQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements IAnswerService {

    @Resource
    public IQuestionService questionService;

    @Resource
    public IAnswerService answerService;

    @Resource
    public IOptionService optionService;

    /**
     * 复制 answerDTO 为 answer
     *
     * @param answerDTOList
     * @return java.util.List<com.murui.applet.entity.Answer>
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 3:30 PM 6/30/2022
     **/
    @Override
    public List<Answer> toAnswers(List<AnswerDTO> answerDTOList) {
        List<Answer> answers = new ArrayList<>();
        Answer answer;
        for (AnswerDTO answerDTO : answerDTOList) {
            // answerDTO 属于选择题的回答
            if (answerDTO.getOptionIds() != null) {
                for (int i = 0; i < answerDTO.getOptionIds().size(); i++) {
                    answer = new Answer();
                    BeanUtil.copyProperties(answerDTO, answer, true);
                    // 复制选项 id
                    answer.setOptionId(answerDTO.getOptionIds().get(i));
                    answers.add(answer);
                }
            }
            // answerDTO 不属于选择题的回答
            else {
                answer = new Answer();
                BeanUtil.copyProperties(answerDTO, answer, true);
                answers.add(answer);
            }
        }
        return answers;
    }

    @Override
    public AnswerVO getAnswerDetail(Paper paper, Long userId) {
        AnswerVO answerVO = new AnswerVO();
        BeanUtil.copyProperties(paper, answerVO, true);

        // 根据 paper_id 查询指定所有问题
        List<Question> questionList = questionService.list(
                new QueryWrapper<Question>()
                        .eq("paper_id", paper.getId())
        );

        List<QuestionAndAnswer> questionAndAnswerList = new ArrayList<>();
        QuestionAndAnswer questionTemp;
        ReadAnswer answerTemp;

        // 遍历所有问题
        for (Question question : questionList) {
            questionTemp = new QuestionAndAnswer();
            BeanUtil.copyProperties(question, questionTemp, true);
            // 查询 question_id、user_id 查询所有回答
            List<Answer> answerList = answerService.list(
                    new QueryWrapper<Answer>()
                            .eq("question_id", question.getId())
                            .eq("user_id", userId)
            );
            List<ReadAnswer> readAnswerList = new ArrayList<>();
            // 遍历所有回答
            for (Answer answer : answerList) {
                answerTemp = new ReadAnswer();
                BeanUtil.copyProperties(answer, answerTemp, true);
                // answer 属于选择题的回答
                if (answer.getOptionId() != null) {
                    Option optionOne = optionService.getOne(
                            new QueryWrapper<Option>()
                                    .eq("id", answer.getOptionId()));
                    answerTemp.setContent(optionOne.getContent());
                }
                readAnswerList.add(answerTemp);
            }
            questionTemp.setAnswers(readAnswerList);
            questionAndAnswerList.add(questionTemp);
        }
        answerVO.setQuestions(questionAndAnswerList);
        return answerVO;
    }
}
