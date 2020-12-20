package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author YiCloud
 * @date 2020/12/19 - 23:28
 */
public interface OssService {

    //上传文件到oss
    String uploadOssFile(MultipartFile file);
}
