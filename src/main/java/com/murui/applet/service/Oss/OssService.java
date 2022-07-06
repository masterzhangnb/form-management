package com.murui.applet.service.Oss;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;


public interface OssService {
    void uploadString();

    String uploadMultipartFile(MultipartFile file);

    String getUrl(String filePath);

    Map<String, String> getPolicy();


}

