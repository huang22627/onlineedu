package com.hjx.onlineedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.hjx.onlineedu.common.EduException;
import com.hjx.onlineedu.pojo.EduSubject;
import com.hjx.onlineedu.mapper.EduSubjectMapper;
import com.hjx.onlineedu.pojo.ExcelSubjectData;
import com.hjx.onlineedu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author hjx
 * @since 2023-02-17
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Resource
    private EduSubjectService subjectService;

    @Override
    public void importSubjectData(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            //1 获取文件输入流
            InputStream inputStream = file.getInputStream();

            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(inputStream, ExcelSubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e) {
            e.printStackTrace();
            throw new EduException(20002,"添加课程分类失败");
        }
    }
    }

