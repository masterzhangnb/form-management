package com.murui.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.murui.applet.entity.WxOpenid;

public interface IWxOpenidService extends IService<WxOpenid> {
    String codeToOpenid(String jsCode);
}
