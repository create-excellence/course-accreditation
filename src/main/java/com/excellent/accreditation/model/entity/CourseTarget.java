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
public class CourseTarget extends BaseEntity {


    @ApiModelProperty("对应的问卷Id")
    private Integer questionnaireId;

    @ApiModelProperty("题目")
    private String title;

    /**
     * 指标点序号
     */
    @ApiModelProperty("指标点Id")
    private Integer pointId;

    /**
     * 选项
     */
    @ApiModelProperty("选项")
    private String options;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String describes;

    /**
     * 选项得分
     */
    @ApiModelProperty("选项得分")
    private String optionsScore;

    /**
     * 总分
     */
    @ApiModelProperty("总分")
    private Integer totalScore;

    /**
     * 序号
     */
    @ApiModelProperty("序号")
    private Integer sequence;
}
