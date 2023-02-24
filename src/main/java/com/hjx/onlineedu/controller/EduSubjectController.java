package com.hjx.onlineedu.controller;



import com.hjx.onlineedu.common.Result;
import com.hjx.onlineedu.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author hjx
 * @since 2023-02-17
 */
@CrossOrigin
@RestController
@RequestMapping("/onlineedu/subject")
public class EduSubjectController {

    @Autowired
    EduSubjectService eduSubjectService;

    // 添加课程分类
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file) {
        //1 获取上传的excel文件 MultipartFile
        //返回错误提示信息
        eduSubjectService.importSubjectData(file,eduSubjectService);
        //判断返回集合是否为空
        return Result.ok();
    }

}

