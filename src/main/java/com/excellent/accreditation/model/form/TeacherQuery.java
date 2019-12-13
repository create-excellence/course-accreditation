package com.excellent.accreditation.model.form;

import com.excellent.accreditation.model.base.BasePage;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @Description: TODO
 * @Author: Nemo
 * @Date: 19:46 2019/12/3
 **/
@Data
public class TeacherQuery extends BasePage {

    @Size(max = 25, message = "工号的字符长度不能超过 {max}")
    private String jno;

    @Size(max = 25, message = "姓名的字符长度不能超过 {max}")
    private String name;

    @Size(max = 25, message = "职称的字符长度不能超过 {max}")
    private String title;
}
