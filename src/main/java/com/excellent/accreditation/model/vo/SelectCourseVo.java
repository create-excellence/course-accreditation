package com.excellent.accreditation.model.vo;

import com.excellent.accreditation.model.entity.CourseClass;
import com.excellent.accreditation.model.entity.SelectCourse;
import lombok.Data;

@Data
public class SelectCourseVo extends SelectCourse {

    private SelectCourse selectCourse;

    /**
     * 课程名称
     */
    private String course;


    private String sno;
    /**
     * 学生姓名
     */
    private String student;

    /**
     * 老师名字
     */
    private String teacher;

    /**
     * 学期
     */
    private String semester;

    /**
     * 课程序号
     */
    private String no;

    /**
     * 学期id
     */
    private Integer semesterId;

    /**
     * 老师id
     */
    private Integer teacherId;

    private Integer startWeek;

    private Integer endWeek;

    /**
     * 0-开课 1-未开课
     */
    private Integer status;

}
