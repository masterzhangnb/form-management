package com.murui.applet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.murui.applet.common.BaseErrorCode;
import com.murui.applet.entity.User;
import com.murui.applet.entity.dto.UserDTO;
import com.murui.applet.entity.vo.UserVO;
import com.murui.applet.exception.ServiceException;
import com.murui.applet.mapper.UserMapper;
import com.murui.applet.service.IUserService;
import com.murui.applet.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.murui.applet.utils.MD5Utils.string2MD5;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    public UserMapper userMapper;

    /**
     * 查询指定的用户信息
     *
     * @param userDTO
     * @return com.murui.applet.entity.User
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 9:32 PM 6/28/2022
     **/
    public User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", string2MD5(userDTO.getPassword()));
        User user = null;
        try {
            user = this.getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            throw new ServiceException(BaseErrorCode.UNKNOWN_ERROR);
        }
        return user;
    }

    @Override
    public UserVO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            UserVO userVO = new UserVO();
            BeanUtil.copyProperties(one, userVO, true);
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userVO.setToken(token);
            return userVO;
        } else {
            return null;
        }
    }


    @Override
    public User getUserOpenid(Long id) {
        return userMapper.getUserOpenid(id);
    }
}
