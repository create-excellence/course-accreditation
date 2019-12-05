package com.excellent.accreditation.model.form;

import com.excellent.accreditation.model.base.BasePage;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @ClassName GraduationDemandQuery
 * @Description TODO
 * @Author 安羽兮
 * @Date 2019/12/514:17
 * @Version 1.0
 **/
@Data
public class GraduationDemandQuery extends BasePage {

    @Size(max = 25, message = "毕业要求编号的字符长度不能超过 {max}")
    private String no;

    @Size(max = 255, message = "毕业要求内容的字符长度不能超过 {max}")
    private String content;
}
