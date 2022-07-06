package com.murui.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.murui.applet.entity.User;
import com.murui.applet.entity.dto.UserDTO;
import com.murui.applet.entity.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IUserService extends IService<User> {

    UserVO login(UserDTO userDTO);

    User getUserOpenid(@Param("id")Long id);
}
