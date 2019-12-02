package com.excellent.accreditation.model.entity;

import com.excellent.accreditation.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class Student extends BaseEntity {

    @ApiModelProperty("学号")
    private String sno;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("年级")
    private String grade;
    @ApiModelProperty("专业Id")
    private Integer majorId;

    /**
     * 正常-0
     */
    @ApiModelProperty("状态 正常-0")
    private Integer status;
    @ApiModelProperty("上此登录数据")
    private LocalDateTime loginTime;


}
