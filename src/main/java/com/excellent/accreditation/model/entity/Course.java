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
public class Course extends BaseEntity {
    @ApiModelProperty("课程代码")
    private String code;

    @ApiModelProperty("课程名称")
    private String name;

    @ApiModelProperty("课程对应学分")
    private Double credit;

    @ApiModelProperty("课程性质")
    private String nature;
}
