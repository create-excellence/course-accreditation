package com.excellent.accreditation.model.form;

import com.excellent.accreditation.model.base.BasePage;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @ClassName CourseQuery
 * @Description TODO
 * @Author 安羽兮
 * @Date 2019/12/316:18
 * @Version 1.0
 **/
@Data
public class CourseQuery extends BasePage {

    @Size(max = 25, message = "编号的字符长度不能超过 {max}")
    private String code;

    @Size(max = 25, message = "课程名称的字符长度不能超过 {max}")
    private String name;

    @Size(max = 25, message = "课程性质的字符长度不能超过 {max}")
    private String nature;
}
