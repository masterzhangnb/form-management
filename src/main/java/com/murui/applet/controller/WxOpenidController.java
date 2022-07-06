package com.murui.applet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.murui.applet.common.PlainResult;
import com.murui.applet.entity.WxOpenid;
import com.murui.applet.service.IWxOpenidService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 */
@RestController
@RequestMapping("/wxOpenid")
public class WxOpenidController {

    @Resource
    public IWxOpenidService wxOpenidService;


    /**
     * 查询所有openid信息
     * @return
     */
//    @GetMapping
//    public PlainResult findAll(){
//        return PlainResult.successResult(wxOpenidService.list());
//    }

    /**
     * 新增用户信息
     * @param wxOpenid
     * @return
     */
    @PostMapping("/add")
    public PlainResult save(@RequestBody WxOpenid wxOpenid){
        return PlainResult.successResult(wxOpenidService.save(wxOpenid));
    }

    /**
     * 通过code获取openid并存入数据库
     * @param jsCode
     * @return
     */
    @PostMapping("/getOpenid")
    public PlainResult codeGetOpenid(@RequestParam String jsCode){
        String openid = wxOpenidService.codeToOpenid(jsCode);
        WxOpenid wxOpenid = wxOpenidService.getOne(new QueryWrapper<WxOpenid>().eq("openid", openid));
        if (wxOpenid == null){
            wxOpenid = new WxOpenid();
            wxOpenid.setOpenid(openid);
            wxOpenidService.save(wxOpenid);
        }else {
            return PlainResult.successResult("用户已记录！");
        }
        return PlainResult.successResult("已获得openid并记录！");
    }

}
