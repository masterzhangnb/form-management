package com.murui.applet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.murui.applet.entity.Option;
import com.murui.applet.entity.Question;
import org.apache.ibatis.annotations.Param;

public interface QuestionMapper extends BaseMapper<Question> {
    int insertQuestion(@Param("question") Question question);

}
