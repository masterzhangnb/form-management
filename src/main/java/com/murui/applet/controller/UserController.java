package com.murui.applet.controller;

import com.murui.applet.common.BaseErrorCode;
import com.murui.applet.common.PlainResult;
import com.murui.applet.entity.User;
import com.murui.applet.entity.dto.UserDTO;
import com.murui.applet.entity.vo.UserVO;
import com.murui.applet.service.IUserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    public IUserService userService;


    /**
     * 查询表 sys_user 的所有记录
     *
     * @return com.murui.applet.common.PlainResult<java.util.List < com.murui.applet.entity.User>>
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 3:54 PM 6/27/2022
     **/
    @GetMapping("/list")
    public PlainResult<List<User>> listUser() {
        return PlainResult.successResult(userService.list());
    }

    /**
     * 新增或修改表 sys_user
     *
     * @param user
     * @return com.murui.applet.common.PlainResult<java.lang.Boolean>
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 4:06 PM 6/27/2022
     **/
    @PostMapping("/saveOrUpdate")
    public PlainResult<Boolean> saveOrUpdate(@RequestBody User user) {
        System.out.println(user);
        return PlainResult.successResult(userService.saveOrUpdate(user));
    }

    /**
     * 登录接口
     *
     * @param userDTO
     * @param result
     * @return com.murui.applet.common.PlainResult<com.murui.applet.entity.vo.UserVO>
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 4:36 PM 6/23/2022
     **/
    @PostMapping("/login")
    public PlainResult<UserVO> login(@RequestBody @Validated UserDTO userDTO, BindingResult result) {
        // 账号或密码不能为空
        if (result.hasErrors())
            return PlainResult.errorResult(BaseErrorCode.ACCOUNT_OR_PASSWORD_ISNULL);

        UserVO vo = userService.login(userDTO);
        // 账号密码错误
        if (vo == null)
            return PlainResult.errorResult(BaseErrorCode.ACCOUNT_OR_PASSWORD_ERROR);

        return PlainResult.successResult(vo);
    }

    /**
     * 根据id获取用户信息及openid（Mybatis 实现）
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public PlainResult<User> getOpenid(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user != null) {
            return PlainResult.successResult(userService.getUserOpenid(id));
        } else {
            return PlainResult.errorResult(BaseErrorCode.USER_NOT_EXIST);
        }
    }
}
