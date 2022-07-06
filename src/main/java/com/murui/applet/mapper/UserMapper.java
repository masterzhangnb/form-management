package com.murui.applet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.murui.applet.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    //根据id获取用户信息
    User getUserOpenid(@Param("id")Long id);
}
