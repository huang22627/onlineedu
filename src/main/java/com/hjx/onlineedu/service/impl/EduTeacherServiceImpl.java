package com.hjx.onlineedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjx.onlineedu.pojo.EduTeacher;
import com.hjx.onlineedu.mapper.EduTeacherMapper;
import com.hjx.onlineedu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjx.onlineedu.vo.TeacherQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author hjx
 * @since 2023-02-17
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    @Resource
    private EduTeacherMapper eduTeacherMapper;
    @Override
    public IPage<EduTeacher> selectPage(Page<EduTeacher> teacherPage, TeacherQueryVo teacherQueryVo) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(teacherQueryVo.getName())) {
            queryWrapper.like("name",teacherQueryVo.getName());
        }

        if (!StringUtils.isEmpty(teacherQueryVo.getLevel())) {
            queryWrapper.like("level",teacherQueryVo.getLevel());
        }

        if (!StringUtils.isEmpty(teacherQueryVo.getBegin())) {
            queryWrapper.ge("gmt_create",teacherQueryVo.getBegin());
        }
        if (!StringUtils.isEmpty(teacherQueryVo.getEnd())) {
            queryWrapper.le("gmt_create",teacherQueryVo.getEnd());
        }

        IPage<EduTeacher> eduTeacherIPage = eduTeacherMapper.selectPage(teacherPage, queryWrapper);
        return eduTeacherIPage;
    }
}
