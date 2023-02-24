package com.hjx.onlineedu.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class OSSConstantUtil implements InitializingBean {
    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    @Value("${endpoint}")
    private String endpoint;
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    @Value("${accessKeyId}")
    private String accessKeyId;
    @Value("${accessKeySecret}")
    private String accessKeySecret;
    // 填写Bucket名称，例如examplebucket。
    @Value("${bucketName}")
    private String bucketName;

    public static String ENDPOINT;
    public static String ACCESSKEYID;
    public static String ACCESSKEYSECRET;
    public static String BUCKETNAME;

    @Override
    public void afterPropertiesSet() throws Exception {
          ENDPOINT=endpoint;
          ACCESSKEYID=accessKeyId;
          ACCESSKEYSECRET=accessKeySecret;
          BUCKETNAME=bucketName;
    }
}
