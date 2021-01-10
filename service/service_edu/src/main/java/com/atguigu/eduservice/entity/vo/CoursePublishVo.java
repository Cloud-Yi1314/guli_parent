package com.atguigu.eduservice.entity.vo;

import lombok.Data;

/**
 * @author YiCloud
 * @date 2021/1/10 - 21:41
 */
@Data
public class CoursePublishVo {

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;

}
