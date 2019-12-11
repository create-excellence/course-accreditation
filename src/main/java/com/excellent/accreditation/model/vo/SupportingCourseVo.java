package com.excellent.accreditation.model.vo;

import com.excellent.accreditation.model.entity.SupportingCourse;
import lombok.Data;

/**
 * @program: course-accreditation
 * @description: SupportingCourseVo类
 * @author: ashe
 * @create: 2019-12-11 20:49
 */
@Data
public class SupportingCourseVo extends SupportingCourse {

    private String courseName;

    private String courseCode;
    //指标点内容
    private String graduationPointContent;
    //指标点编号
    private String graduationPointNo;
}
