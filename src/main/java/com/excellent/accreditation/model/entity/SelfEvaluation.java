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
public class SelfEvaluation extends BaseEntity {

    @ApiModelProperty("学生Id")
    private Integer studentId;


    @ApiModelProperty("专业名称")
    private Integer courseTargetId;

    /**
     * 评价档次
     */
    @ApiModelProperty("评价档次")
    private String answer;

    /**
     * 得分
     */
    @ApiModelProperty("得分")
    private Integer score;


}
