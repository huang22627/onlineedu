package com.hjx.onlineedu.controller;

import com.hjx.onlineedu.common.Result;
import com.hjx.onlineedu.service.FileUploadService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileUploadController {
    @Resource
    private FileUploadService fileUploadService;
    @PostMapping("/upload")
    public Result upload(MultipartFile photo){
        String url = fileUploadService.upload(photo);
        return Result.ok().data("url",url);
    }
}
