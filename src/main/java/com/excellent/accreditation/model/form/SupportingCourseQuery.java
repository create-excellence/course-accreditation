package com.excellent.accreditation.model.form;

import com.excellent.accreditation.model.base.BasePage;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @program: course-accreditation
 * @description: 毕业要求指标点支撑课程的查询表单
 * @author: ashe
 * @create: 2019-12-11 19:20
 */
@Data
public class SupportingCourseQuery extends BasePage {

    @Size(max = 25, message = "课程名称的字符长度不能超过 {max}")
    private String courseName;

    @Size(max = 25, message = "指标点名称长度不能超过 {max}")
    private String graduationPointContent;

    @Size(max = 12, message = "指标点Id长度不能超过 {max}")
    private Integer graduationPointId;
}
