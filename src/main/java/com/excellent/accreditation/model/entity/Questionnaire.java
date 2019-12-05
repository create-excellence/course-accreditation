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
 * @since 2019-11-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel
public class Questionnaire extends BaseEntity {

    @ApiModelProperty("问卷名称")
    private String name;
    @ApiModelProperty("问卷对应班级")
    private Integer courseClassId;
    @ApiModelProperty("问卷总分")
    private Integer totalScore;
    @ApiModelProperty("问卷描述")
    private String describes;
}
