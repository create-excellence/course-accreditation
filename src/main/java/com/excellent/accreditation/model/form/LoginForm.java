package com.excellent.accreditation.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @ClassName LoginForm
 * @Description TODO
 * @Author 安羽兮
 * @Date 2019/11/2522:42
 * @Version 1.0
 **/
@Data
public class LoginForm {

    @NotBlank(message = "编号不能为空")
    @Size(max = 25, message = "编号的字符长度不能超过 {max}")
    private String code;

    @NotBlank(message = "密码不能为空")
    @Size(max = 255, message = "密码的字符长度不能超过 {max}")
    private String password;
}
