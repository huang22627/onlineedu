package com.hjx.onlineedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjx.onlineedu.common.Result;
import com.hjx.onlineedu.pojo.EduTeacher;
import com.hjx.onlineedu.service.EduTeacherService;
import com.hjx.onlineedu.vo.TeacherQueryVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.geom.QuadCurve2D;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author hjx
 * @since 2023-02-17
 */
@CrossOrigin
@RestController
@RequestMapping("/onlineedu/teacher")
public class EduTeacherController {
    @Resource
    private EduTeacherService eduTeacherService;

    @GetMapping("/findallteacher")
    public List<EduTeacher> getAllTeacher(){
        return eduTeacherService.list(null);
    }
    @GetMapping("/getTeachersPage/{page}/{limit}")
    public Result getTeachersPage(@PathVariable Long page,
                                  @PathVariable Long limit){
        Page<EduTeacher> teacherPage = new Page(page,limit);

        IPage<EduTeacher> teacherIpage = eduTeacherService.page(teacherPage, null);
        return Result.ok().data("total",teacherIpage.getTotal()).data("rows",teacherIpage.getRecords());
    }

    @PostMapping("/getTeachersPage/{page}/{limit}")
    public Result selectPageParams(@PathVariable Long page,
                                   @PathVariable Long limit,
                                   @RequestBody TeacherQueryVo teacherQueryVo){
        Page<EduTeacher> teacherPage = new Page(page,limit);
       IPage<EduTeacher> teacherIpage = eduTeacherService.selectPage(teacherPage,teacherQueryVo);

        return Result.ok().data("total",teacherIpage.getTotal()).data("rows",teacherIpage.getRecords());
    }

    @GetMapping("/getTeacherById/{id}")
    public Result getTeacherById(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return Result.ok().data("teacher",eduTeacher);
    }

    @DeleteMapping("/deleteTeacher/{id}")
    public Result deleteTeacherById(@PathVariable String id){
         eduTeacherService.removeById(id);
        return Result.ok();
    }
    @PostMapping("/save")
    public Result saveUser(@RequestBody EduTeacher eduTeacher){
        eduTeacherService.save(eduTeacher);
        return Result.ok();
    }

    @PostMapping("/update")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher){
        eduTeacherService.updateById(eduTeacher);
        return Result.ok();
    }

}

