package com.excellent.accreditation.model.entity;

import com.excellent.accreditation.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class GraduationPoint extends BaseEntity {

    @ApiModelProperty("指标点编号")
    private String no;
    @ApiModelProperty("毕业要求Id")
    private Integer graduationDemandId;
    @ApiModelProperty("指标点内容")
    private String content;
}
