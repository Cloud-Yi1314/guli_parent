package com.atguigu.eduservice.controller;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author YiCloud
 * @since 2020-12-06
 */
//@Api(description = "讲师管理")
@Api(produces = "讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    //把service注入
    @Autowired
    private EduTeacherService teacherService;

    //1查询讲师表所有数据
    //rest风格
    @ApiOperation(value="查询讲师表所有数据")
    @GetMapping("findAll")
    public R findAllTecher(){
        //调用service的方法实现查询所有
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    //2逻辑删除讲师的方法
    @ApiOperation(value="逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name="id",value="讲师id",required = true) @PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

    //3.分页查询讲师的方法
    //page 当前页
    //limit 每页记录数
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageListTeacher(
            @ApiParam(name = "page",value = "当前页码",required = true)
            @PathVariable long page,
            @ApiParam(name = "limit",value = "每页记录数",required = true)
            @PathVariable long limit){

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<EduTeacher>(page,limit);
        //调用方法实现分页
        teacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }

    //4.条件查询带分页的方法
    @PostMapping(value ="pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(
            @PathVariable long current,
            @PathVariable long limit,
            @RequestBody(required = false) TeacherQuery teacherQuery){
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<EduTeacher>(current,limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("begin",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("end",end);
        }

        //调用方法实现分页
        teacherService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }

    //5.添加讲师接口的方法
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else{
            return R.error();
        }
    }

    //6.根据讲师id进行查询
    @GetMapping(value = "getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }

    //7.讲师修改功能
    @PostMapping(value = "updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.updateById(eduTeacher);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }
}

