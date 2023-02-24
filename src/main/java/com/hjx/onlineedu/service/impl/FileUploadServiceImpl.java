package com.hjx.onlineedu.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.hjx.onlineedu.service.FileUploadService;
import com.hjx.onlineedu.utils.OSSConstantUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Override
    public String upload(MultipartFile multipartFile) {
        String url="";
        OSS ossClient = new OSSClientBuilder().build(OSSConstantUtil.ENDPOINT, OSSConstantUtil.ACCESSKEYID, OSSConstantUtil.ACCESSKEYSECRET);
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
            String filename = multipartFile.getOriginalFilename();

            String hzName = filename.substring(filename.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString().replace("-","");
            filename=uuid+hzName;

            String dateTime = new DateTime().toString("yyyy-MM-dd");
            filename=dateTime+"/"+filename;

            PutObjectRequest putObjectRequest = new PutObjectRequest(OSSConstantUtil.BUCKETNAME, filename, inputStream);
            putObjectRequest.setProcess("true");
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            // 如果上传成功，则返回200。
            System.out.println(result.getResponse().getStatusCode());
            //https://hjxuse.oss-cn-beijing.aliyuncs.com/1.jpg
             url="https://"+OSSConstantUtil.BUCKETNAME+"."+OSSConstantUtil.ENDPOINT+
                    "/"+filename;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (ossClient!=null) {
                ossClient.shutdown();
            }
        }

        return url;
    }
}
