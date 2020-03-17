package com.excellent.accreditation.model.form;

import com.excellent.accreditation.model.base.BasePage;
import lombok.Data;


@Data
public class SelectCourseQuery extends BasePage {

    /**
     * 课程序号
     */
    private String no;

    /**
     * 课程名称
     */
    private String course;

    /**
     * 学生姓名
     */
    private String student;

    /**
     * 学生ID
     */
    private Integer studentId;

    /**
     * 老师姓名
     */
    private String teacher;


    private String semester;
}
