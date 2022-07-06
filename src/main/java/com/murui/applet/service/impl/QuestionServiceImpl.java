package com.murui.applet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.murui.applet.entity.Option;
import com.murui.applet.entity.Question;
import com.murui.applet.entity.dto.QuestionDTO;
import com.murui.applet.mapper.QuestionMapper;
import com.murui.applet.service.IOptionService;
import com.murui.applet.service.IQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
    @Resource
    public IQuestionService questionService;

    @Resource
    public IOptionService optionService;

    /**
     * 新增问题或修改问题
     *
     * @param questionDTO
     * @return java.lang.Boolean
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 11:39 AM 6/27/2022
     **/
    @Override
    public Boolean saveQuestionAndOption(QuestionDTO questionDTO) {
        System.out.println(questionDTO);
        Question question = new Question();
        // 将 questionDTO 对象的属性复制到 question 对象
        BeanUtil.copyProperties(questionDTO, question, true);

        // question 的 TableId 注解（主键id）存在就更新记录，否则插入一条记录
        questionService.saveOrUpdate(question);

        // 问题为单选或者多选
        if (questionDTO.getOptions() != null) {
            for (Option option : questionDTO.getOptions())
                // 为每个选项指定所属的问题id（question_id)
                option.setQuestionId(question.getId());
            // 对 sys_option 批量更新或插入记录
            optionService.saveOrUpdateBatch(questionDTO.getOptions());
        }
        return true;
    }
}
