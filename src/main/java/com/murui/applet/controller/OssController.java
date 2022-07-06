package com.murui.applet.controller;

import com.murui.applet.common.PlainResult;
import com.murui.applet.service.Oss.OssService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName OssController
 * @Description TODO
 * @Author masterzhang && masterzhangnb@gmail.com
 * @Date 7/4/2022 3:57 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/oss")
public class OssController {
    @Resource
    public OssService ossService;

    /**
     * 网页上传文件
     *
     * @param file
     * @return com.murui.applet.common.PlainResult<java.lang.String>
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 11:26 AM 7/4/2022
     **/
    @PostMapping("/uploadFile")
    public PlainResult<String> uploadFile(@RequestParam MultipartFile file){
        return PlainResult.successResult(ossService.uploadMultipartFile(file));
    }

    /**
     * 获取临时访问路径的签名 url
     *
     * @param filePath
     * @return com.murui.applet.common.PlainResult<java.lang.String>
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 2:54 PM 7/4/2022
     **/
    @PostMapping("/getUrl")
    public PlainResult<String> getUrl(@RequestParam String filePath){
        return PlainResult.successResult(ossService.getUrl(filePath));
    }

    /**
     * 返回上传 Policy 和签名
     *
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 3:59 PM 7/4/2022
     **/
    @GetMapping("/getPolicy")
    public PlainResult<Map<String, String>> getPolicy(){
        return PlainResult.successResult(ossService.getPolicy());
    }



}
