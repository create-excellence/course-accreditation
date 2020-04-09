package com.excellent.accreditation.model.form;

import com.excellent.accreditation.model.base.BasePage;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @description:
 * @author: ashe
 * @DATE: 2020/4/7 22:22
 */
@Data
public class CourseEvaluationStudentQuery extends BasePage {
    @Size(max = 25, message = "学生姓名的字符长度不能超过 {max}")
    String student;

    @Size(max = 12, message = "课程评价Id的字符长度不能超过 {max}")
    Integer courseEvaluationId;
}
