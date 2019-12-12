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
public class CourseClass extends BaseEntity {

    /**
     * 课程序号
     */
    @ApiModelProperty("课程序号")
    private String no;

    /**
     * 学期id
     */
    @ApiModelProperty("学期Id")
    private Integer semesterId;

    /**
     * 课程id
     */
    @ApiModelProperty("课程Id")
    private Integer courseId;

    /**
     * 老师id
     */
    @ApiModelProperty("执教老师Id")
    private Integer teacherId;

    @ApiModelProperty("开课时间")
    private Integer startWeek;

    @ApiModelProperty("结课时间")
    private Integer endWeek;

    /**
     * 0-开课 1-未开课
     */
    @ApiModelProperty("状态码 0-开课 1-未开课")
    private Integer status;


}
