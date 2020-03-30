package com.excellent.accreditation.model.form;

import com.excellent.accreditation.model.base.BasePage;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @description:
 * @author: ashe
 * @DATE: 2020/3/28 17:07
 */
@Data
public class CourseEvaluationQuery extends BasePage {

    @Size(max = 12, message = "状态码长度不能超过 {max}")
    private Integer status;
    @Size(max = 12, message = "时间排序状态码不能超过 {max}")
    private Integer  timeOrder;
    @Size(max = 25, message = "名称字符长度不能超过 {max}")
    private String  name;

}
