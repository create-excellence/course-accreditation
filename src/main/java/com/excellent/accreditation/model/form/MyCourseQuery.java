package com.excellent.accreditation.model.form;

import com.excellent.accreditation.model.base.BasePage;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @description:
 * @author: ashe
 * @DATE: 2020/3/27 11:44
 */
@Data
public class MyCourseQuery  extends BasePage {
    @Size(max = 12, message = "学期Id的字符长度不能超过 {max}")
    private Integer semesterId;
}
