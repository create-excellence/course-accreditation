package com.excellent.accreditation.model.form;

import com.excellent.accreditation.model.base.BasePage;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @ClassName GraduationPointQuery
 * @Description TODO
 * @Author 安羽兮
 * @Date 2019/12/514:35
 * @Version 1.0
 **/
@Data
public class GraduationPointQuery extends BasePage {

    @Size(max = 25, message = "指标点编号的字符长度不能超过 {max}")
    private String no;

    @Size(max = 25, message = "指标点内容的字符长度不能超过 {max}")
    private String content;
}
