package com.murui.applet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.murui.applet.entity.Option;
import com.murui.applet.entity.Paper;
import org.apache.ibatis.annotations.Param;

public interface OptionMapper extends BaseMapper<Option> {
    int insertOption(@Param("option") Option option);
}
