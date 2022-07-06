package com.murui.applet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.murui.applet.entity.Paper;
import com.murui.applet.entity.User;
import org.apache.ibatis.annotations.Param;

public interface PaperMapper extends BaseMapper<Paper> {
    int insertPaper(@Param("paper") Paper paper);
}
