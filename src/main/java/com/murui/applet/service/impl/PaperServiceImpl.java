package com.murui.applet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.murui.applet.entity.Option;
import com.murui.applet.entity.Paper;
import com.murui.applet.entity.Question;
import com.murui.applet.entity.vo.PaperVO;
import com.murui.applet.entity.QuestionInfo.QuestionAndOption;
import com.murui.applet.mapper.OptionMapper;
import com.murui.applet.mapper.PaperMapper;
import com.murui.applet.mapper.QuestionMapper;
import com.murui.applet.service.IOptionService;
import com.murui.applet.service.IPaperService;
import com.murui.applet.service.IQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements IPaperService {

    @Resource
    public PaperMapper paperMapper;

    @Resource
    public QuestionMapper questionMapper;

    @Resource
    public OptionMapper optionMapper;

    @Resource
    public IQuestionService questionService;

    @Resource
    public IOptionService optionService;

    /**
     * 查询 Paper 对象的所有问题和选项（如果有），并返回相应的 PaperVO 对象
     *
     * @param paper
     * @return com.murui.applet.entity.vo.PaperVO
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 7:46 PM 6/29/2022
     **/
    @Override
    public PaperVO getPaperDetail(Paper paper) {
        PaperVO paperVO = new PaperVO();
        // 将 paper 对象的属性复制到 paperVO 对象
        BeanUtil.copyProperties(paper, paperVO, true);

        // 查询对应 paper 下的所有问题
        List<Question> questionList = questionService.list(
                new QueryWrapper<Question>()
                        .eq("paper_id", paper.getId())
        );

        // 查询对应 paper 下的所有问题和选项（针对选择题）
        List<QuestionAndOption> questionAndOptionList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionAndOption vo = new QuestionAndOption();
            BeanUtil.copyProperties(question, vo, true);
            List<Option> options = optionService.list(
                    new QueryWrapper<Option>()
                            .eq("question_id", question.getId())
                            .ne("is_deleted", true));
            vo.setOptions(options);
            questionAndOptionList.add(vo);
        }
        paperVO.setQuestions(questionAndOptionList);

        return paperVO;
    }

    /**
     * 复制（插入） Paper 对象和其所有问题和选项（如果有）
     *
     * @param paper
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 7:52 PM 6/29/2022
     **/
    @Override
    public void copyPaper(Paper paper) {
        // 复制（插入）Paper 对象，并将问卷状态 status 设置为 0（未启用）
        paper.setStatus(0);
        paperMapper.insertPaper(paper);

        // 复制 paper 对象的问题 Question
        // 查询对应 paper 下的所有问题
        List<Question> questionList = questionService.list(
                new QueryWrapper<Question>()
                        .eq("paper_id", paper.getId())
        );
        for (Question question : questionList) {
            // 复制（插入）对应 paper 下的所有 Question 对象
            questionMapper.insertQuestion(question);
            // 查询对应 question 下的所有回答（如果有）
            if (question.getQuestionType() == 1 || question.getQuestionType() == 2) {
                List<Option> optionList = optionService.list(
                        new QueryWrapper<Option>()
                                .eq("question_id", question.getId())
                );
                // 复制（插入）对应 quesiton 下的所有 option 对象
                for (Option option : optionList)
                    optionMapper.insertOption(option);
            }

        }
    }
}
