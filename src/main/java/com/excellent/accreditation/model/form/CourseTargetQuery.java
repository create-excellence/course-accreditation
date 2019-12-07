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
public class CourseTargetQuery extends BasePage {


    @Size(max = 25, message = "指标点的字符长度不能超过 {max}")
    private String pointName;


}
