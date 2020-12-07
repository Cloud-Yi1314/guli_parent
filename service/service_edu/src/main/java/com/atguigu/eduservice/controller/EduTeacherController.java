package com.atguigu.eduservice.controller;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
}

