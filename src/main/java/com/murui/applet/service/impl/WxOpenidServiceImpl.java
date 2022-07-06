package com.murui.applet.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.murui.applet.common.BaseErrorCode;
import com.murui.applet.entity.WxOpenid;
import com.murui.applet.exception.ServiceException;
import com.murui.applet.mapper.WxOpenidMapper;
import com.murui.applet.service.IWxOpenidService;
import com.murui.applet.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WxOpenidServiceImpl extends ServiceImpl<WxOpenidMapper, WxOpenid> implements IWxOpenidService {
    @Resource
    private HttpClientUtil httpClientUtil;

    @Value("${wx.jscode2sessionUrl}")
    private String jscode2sessionUrl;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.appsecret}")
    private String appsecret;

    /**
     * 通过code获取openid
     * @param jsCode
     * @return
     */
    @Override
    public String codeToOpenid(String jsCode) {
        String jscode2session = jscode2sessionUrl+"?appid="+appid+"&secret="+appsecret+"&js_code="+jsCode+"&grant_type=authorization_code";
        String result = httpClientUtil.sendHttpGet(jscode2session);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String openid;
        try{
            openid = jsonObject.get("openid").toString();
            return openid;
        }catch (Exception e){
            throw new ServiceException(BaseErrorCode.OPENID_NOT_EXIST);
        }
    }
}
