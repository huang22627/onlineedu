package com.hjx.onlineedu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjx.onlineedu.pojo.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjx.onlineedu.vo.TeacherQueryVo;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author hjx
 * @since 2023-02-17
 */
public interface EduTeacherService extends IService<EduTeacher> {

    IPage<EduTeacher> selectPage(Page<EduTeacher> teacherPage, TeacherQueryVo teacherQueryVo);
}
