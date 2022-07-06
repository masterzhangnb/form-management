package com.murui.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.murui.applet.entity.Paper;
import com.murui.applet.entity.vo.PaperVO;

public interface IPaperService extends IService<Paper> {

    PaperVO getPaperDetail(Paper paper);
    void copyPaper(Paper paper);
}
