package com.excellent.accreditation.model.form;

import com.excellent.accreditation.model.base.BasePage;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @ClassName MajorQuery
 * @Description TODO
 * @Author 安羽兮
 * @Date 2019/12/49:45
 * @Version 1.0
 **/
@Data
public class RoleQuery extends BasePage {

    @Size(max = 25, message = "角色代码的字符长度不能超过 {max}")
    private String code;

    @Size(max = 25, message = "角色名称的字符长度不能超过 {max}")
    private String role;
}
