package com.hjx.onlineedu.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    String upload(MultipartFile multipartFile);
}
