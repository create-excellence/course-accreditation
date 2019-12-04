package com.excellent.accreditation.model.form;

import com.excellent.accreditation.model.base.BasePage;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @Author 安羽兮
 * @Description //TODO
 * @Date 8:59 2019/12/4
 **/
@Data
public class StudentQuery extends BasePage {

    @Size(max = 25, message = "学号的字符长度不能超过 {max}")
    private String sno;

    @Size(max = 25, message = "姓名的字符长度不能超过 {max}")
    private String name;
}
