package com.murui.applet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.murui.applet.common.PlainResult;
import com.murui.applet.entity.Answer;
import com.murui.applet.entity.Paper;
import com.murui.applet.entity.dto.AnswerDTO;
import com.murui.applet.entity.dto.ReadAnswerDTO;
import com.murui.applet.entity.vo.AnswerVO;
import com.murui.applet.service.IAnswerService;
import com.murui.applet.service.IPaperService;
import com.murui.applet.service.Oss.OssService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AnswerController
 * @Description 处理答题表
 * @Author masterzhang && masterzhangnb@gmail.com
 * @Date 6/23/2022 9:36 PM
 * @Version 1.0
 **/

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Resource
    public IAnswerService answerService;

    @Resource
    public IPaperService paperService;

    /**
     * 查询 sys_answer 表的所有记录
     * 
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 3:28 PM 6/23/2022
     * @return com.murui.applet.common.PlainResult
    **/
    @GetMapping("/list")
    public PlainResult listAnswer(){
        return PlainResult.successResult(answerService.list());
    }

    /**
     * 上传答卷
     * @param answersDTOList
     * @return
     */
    @PostMapping("/submit")
    public PlainResult submit(@RequestBody List<AnswerDTO> answersDTOList){
        List<Answer> answers = answerService.toAnswers(answersDTOList);
        return PlainResult.successResult(answerService.saveBatch(answers));
    }

    /**
     *查询用户答卷信息
     * @param readAnswerDTO
     * @return
     */
    @PostMapping("/getAnswer")
    public PlainResult getAnswerDetail(@RequestBody ReadAnswerDTO readAnswerDTO){
        Paper paper = paperService.getById(readAnswerDTO.getPaperId());
        AnswerVO answerDetail = answerService.getAnswerDetail(paper,readAnswerDTO.getUserId());
        return PlainResult.successResult(answerDetail);
    }

    /**
     * 根据 questionId 查询问题
     *
     * @param questionId 问卷 id
     * @return com.murui.applet.common.PlainResult<java.util.List<com.murui.applet.entity.Answer>>
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 11:11 AM 6/30/2022
     **/
    @PostMapping("/listAnswerByQuestionId")
    public PlainResult<List<Answer>> listAnswerByQuestionId(@RequestParam Long questionId){
        List<Answer>  answerList = answerService.list(
                new QueryWrapper<Answer>()
                        .eq("question_id",questionId)
        );
        return PlainResult.successResult(answerList);
    }

    /**
     * 根据 paperId 查询返回用户列表（user_id, create_time）,并使用 user_id 分组
     *
     * @param paperId 问卷id
     * @return com.murui.applet.common.PlainResult<java.util.List<com.murui.applet.entity.Answer>>
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 2:45 PM 6/30/2022
     **/
    @PostMapping("/listUserByPaperId")
    public PlainResult<List<Answer>> userList(@RequestParam Long paperId){
        List<Answer> answerList = answerService.list(
                new QueryWrapper<Answer>()
                        .select("user_id", "create_time")
                        .eq("paper_id",paperId)
                        .groupBy("user_id"));
        return PlainResult.successResult(answerList);
    }

}
