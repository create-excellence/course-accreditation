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
public class GraduationDemand extends BaseEntity {

    @ApiModelProperty("专业Id")
    private Integer majorId;

    /**
     * 毕业要求编号
     */
    @ApiModelProperty("毕业要求编号")
    private String no;

    /**
     * 必要要求内容
     */
    @ApiModelProperty("毕业要求内容")
    private String content;


}
