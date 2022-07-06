package com.murui.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.murui.applet.entity.Option;
import com.murui.applet.mapper.OptionMapper;
import com.murui.applet.service.IOptionService;
import org.springframework.stereotype.Service;

@Service
public class OptionServiceImpl extends ServiceImpl<OptionMapper, Option> implements IOptionService {
}
