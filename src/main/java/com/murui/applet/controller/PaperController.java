package com.murui.applet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.murui.applet.common.PlainResult;
import com.murui.applet.entity.Paper;
import com.murui.applet.entity.dto.PaperBasicDTO;
import com.murui.applet.entity.vo.PaperVO;
import com.murui.applet.service.IPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @ClassName AnswerController
 * @Description 处理问卷表
 * @Author masterzhang && masterzhangnb@gmail.com
 * @Date 6/23/2022 9:36 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private IPaperService paperService;

    /**
     * 根据 user_id 和 paper_title 查询表 sys_paper 的记录
     *
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 10:31 AM 6/27/2022
     * @param paperBasicDTO
     * @return com.murui.applet.common.PlainResult<java.util.List<com.murui.applet.entity.Paper>>
    **/
    @PostMapping(value = "/list")
    public PlainResult<List<Paper>> listPaper(@RequestBody PaperBasicDTO paperBasicDTO) {
        System.out.println(paperBasicDTO);
        List<Paper> papers = paperService.list(
                new QueryWrapper<Paper>()
                        .eq("user_id", paperBasicDTO.getUserId())
                        .like("paper_title", paperBasicDTO.getPaperTitle())
                        .ne("status", 2)
        );
        return PlainResult.successResult(papers);
    }

    /**
     * 新增或修改表 sys_question
     *
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 10:34 AM 6/27/2022
     * @param paper
     * @return com.murui.applet.common.PlainResult<java.lang.Boolean>
    **/
    @PostMapping(value = "/saveOrUpdate")
    public PlainResult<Boolean> saveOrUpdate(@RequestBody Paper paper) {
        boolean result = paperService.saveOrUpdate(paper);
        return PlainResult.successResult(result);
    }

    /**
     * 修改问卷状态
     *
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 11:06 AM 6/27/2022
     * @param paperBasicDTO
     * @param bindingResult
     * @return com.murui.applet.common.PlainResult<java.lang.Boolean>
    **/
    @PostMapping(value = "/updatePaperStatus")
    public PlainResult<Boolean> updatePaperStatus(@RequestBody @Validated PaperBasicDTO paperBasicDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return PlainResult.errorResult(400, "status不在范围内");

        if(paperService.getById(paperBasicDTO.getId()) == null)
            return PlainResult.errorResult(400, "问卷不存在");

        boolean result = paperService.update(
                new UpdateWrapper<Paper>()
                        .eq("id", paperBasicDTO.getId())
                        .set("status", paperBasicDTO.getStatus()));
        return PlainResult.successResult(result);
    }

    /*
     * 根据问卷 id 查询问卷信息
     *
     * @param id
     * @return com.murui.applet.common.PlainResult<java.lang.PaperVO>
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 2:25 PM 6/27/2022
     **/
    @PostMapping(value = "/listPaperDetail")
    public PlainResult<PaperVO> listPaperDetail(@RequestParam Long id) {
        Paper paper = paperService.getById(id);
        if (paper == null)
            return PlainResult.errorResult(400, "问卷不存在");

        PaperVO paperVO = paperService.getPaperDetail(paper);
        return PlainResult.successResult(paperVO);
    }

    /**
     * 复制（插入） Paper 对象和其所有问题和选项（如果有）
     *
     * @param id paper 对象的主键
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 10:55 AM 6/30/2022
     **/
    @GetMapping(value="/copy{id}")
    public void copyPaper(@PathVariable Long id){
        Paper paper = paperService.getById(id);
        paperService.copyPaper(paper);

    }
}
