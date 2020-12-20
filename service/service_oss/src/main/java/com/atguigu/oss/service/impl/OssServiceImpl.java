package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstanPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author YiCloud
 * @date 2020/12/19 - 23:28
 */
@Service
public class OssServiceImpl implements OssService {

    //上传头像到oss实现
    @Override
    public String uploadOssFile(MultipartFile file) {
        String endpoint = ConstanPropertiesUtils.END_POINT;
        String accessKeyId = ConstanPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstanPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstanPropertiesUtils.BUCKET_NAME;

        try{
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            //文件名
            String fileName = file.getOriginalFilename();

            //1.文件名称添加随机唯一值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            fileName = uuid+"_"+fileName;

            //2.获取当前日期
            String dataPath = new DateTime().toString("yyyy/MM/dd");
            fileName = dataPath+"/"+fileName;

            //参数1 Bucket名称
            //参数2 oss文件路径和文件名称
            //参数3 上传文件输入流
            ossClient.putObject(bucketName,fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //返回上传后路径
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
