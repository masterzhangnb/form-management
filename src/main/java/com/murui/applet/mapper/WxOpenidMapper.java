package com.murui.applet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.murui.applet.entity.WxOpenid;
import org.apache.ibatis.annotations.Param;

public interface WxOpenidMapper extends BaseMapper<WxOpenid> {
    //根据用户id获取openid
    String getOpenid(@Param("id") Long id);

}
