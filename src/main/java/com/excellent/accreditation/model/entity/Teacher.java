package com.excellent.accreditation.model.entity;

import com.excellent.accreditation.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author ashe
 * @since 2019-11-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel
public class Teacher extends BaseEntity {

    /**
     * 工号
     */
    @ApiModelProperty("工号")
    private String jno;
    @ApiModelProperty("密码")
    private String password;

    /**
     * 角色：teacher或者admin
     */
    @ApiModelProperty("角色")
    private String role;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("性别")
    private String sex;

    /**
     * 职称
     */
    @ApiModelProperty("职称")
    private String title;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("出生日期")
    private LocalDate birth;
    @ApiModelProperty("毕业学校")
    private String graduateSchool;
    @ApiModelProperty("毕业转移")
    private String graduateMajor;
    @ApiModelProperty("上传登录时间")
    private LocalDateTime loginTime;


}
