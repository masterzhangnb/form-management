package com.murui.applet.controller;

import com.murui.applet.common.PlainResult;
import com.murui.applet.entity.Answer;
import com.murui.applet.entity.Option;
import com.murui.applet.service.IOptionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName OptionController
 * @Description 处理问题选项表
 * @Author masterzhang && masterzhangnb@gmail.com
 * @Date 6/23/2022 9:36 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/option")
public class OptionController {

    @Resource
    public IOptionService optionService;

    /**
     * 查询 sys_option 表的所有记录
     *
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 4:37 PM 6/26/2022
     * @return com.murui.applet.common.PlainResult<java.util.List<com.murui.applet.entity.Option>>
    **/
    @GetMapping("/list")
    public PlainResult<List<Option>> listOption(){
        return PlainResult.successResult(optionService.list());
    }

    /**
     * 批量删除选项
     * 注解 @RequestBody 用来接收前端传递给后端的 json 字符串的数据
     *
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 5:14 PM 6/23/2022
     * @param ids
     * @return com.murui.applet.common.PlainResult
    **/
    @PostMapping("/del/batch")
    public PlainResult<Boolean> delOption(@RequestBody List<Long> ids){
        // 根据 id 批量查询 sys_option 表的记录
        List<Option> options = optionService.listByIds(ids);
        for (Option option : options)
            option.setIsDeleted(true);
        optionService.updateBatchById(options);
        return PlainResult.successResult(true);
    }
}
