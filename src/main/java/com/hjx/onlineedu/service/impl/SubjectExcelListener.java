package com.hjx.onlineedu.service.impl;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjx.onlineedu.common.EduException;
import com.hjx.onlineedu.pojo.EduSubject;
import com.hjx.onlineedu.pojo.ExcelSubjectData;
import com.hjx.onlineedu.service.EduSubjectService;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {
    private EduSubjectService subjectService;
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        if (excelSubjectData == null) {
            throw new EduException(20001, "文件数据为空！");
        }

        // 一行一行的读

        EduSubject eduOneSubject = this.existOneSubject(subjectService, excelSubjectData.getOneSubjectName());
        if (eduOneSubject == null) { // 没有相同的一级分类
            eduOneSubject = new EduSubject();
            eduOneSubject.setParentId("0");
            eduOneSubject.setTitle(excelSubjectData.getOneSubjectName());

            subjectService.save(eduOneSubject);
        }

        // 判断二级分类
        String pid = eduOneSubject.getId();
        EduSubject eduTwoSubject = this.existTwoSubject(subjectService, excelSubjectData.getTwoSubjectName(), pid);
        if (eduTwoSubject == null) { // 没有相同的一级分类
            eduTwoSubject = new EduSubject();
            eduTwoSubject.setParentId(pid);
            eduTwoSubject.setTitle(excelSubjectData.getTwoSubjectName());

            subjectService.save(eduTwoSubject);
        }


    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    // 判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        // 查询数据库判断一级分类是否存在
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");

        EduSubject eduSubjectOne = subjectService.getOne(wrapper);
        return eduSubjectOne;
    }

    // 判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject eduSubjectTwo = subjectService.getOne(wrapper);
        return eduSubjectTwo;
    }
}

