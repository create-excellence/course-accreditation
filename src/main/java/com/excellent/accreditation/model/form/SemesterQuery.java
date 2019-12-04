package com.excellent.accreditation.model.form;

import com.excellent.accreditation.model.base.BasePage;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @ClassName SemesterQuery
 * @Description TODO
 * @Author 安羽兮
 * @Date 2019/12/422:27
 * @Version 1.0
 **/
@Data
public class SemesterQuery extends BasePage {

    @Size(max = 25, message = "学期的字符长度不能超过 {max}")
    private String semester;
}
