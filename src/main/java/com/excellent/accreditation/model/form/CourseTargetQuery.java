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


    @Size(max = 25, message = "题目的字符长度不能超过 {max}")
    private String title;

    @Size(max = 12, message = "选项的字符长度不能超过 {max}")
    private Integer option;

    @Size(max = 12, message = "问卷Id的字符长度不能超过 {max}")
    private Integer questionnaireId;
}
