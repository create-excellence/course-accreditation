package com.excellent.accreditation.model.form;

import com.excellent.accreditation.model.base.BasePage;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @program: course-accreditation
 * @description: 课程对应班级查询的表单类
 * @author: ashe
 * @create: 2019-12-06 10:23
 */
@Data
public class CourseClassQuery extends BasePage {


    @Size(max = 25, message = "教师的名字字符长度不能超过 {max}")
    private String teacher;

    @Size(max = 25, message = "课程名称字符长度不能超过 {max}")
    private String course;

    @Size(max = 25, message = "学期名称字符长度不能超过 {max}")
    private String semester;

    @Size(max = 12, message = "课程Id的字符长度不能超过 {max}")
    private Integer courseId;

    @Size(max = 12, message = "老师Id的字符长度不能超过 {max}")
    private Integer teacherId;

    @Size(max = 12, message = "学期Id的字符长度不能超过 {max}")
    private Integer semesterId;

}
