package com.excellent.accreditation.model.form;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @Author 安羽兮
 * @Description //TODO
 * @Date 10:17 2020/3/26
 **/
@Data
public class PasswordForm {
    @Size(max = 25, message = "密码长度不能超过 {max}")
    private String oldPassword;

    @Size(max = 25, message = "密码长度不能超过 {max}")
    private String newPassword;
}
