package com.excellent.mybatis.entity;

import com.excellent.common.BaseEntity;
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
public class CourseClass extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 课程序号
     */
    private String no;

    /**
     * 学期id
     */
    private Integer semesterId;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 老师id
     */
    private Integer teacherId;

    private Integer startWeek;

    private Integer endWeek;

    /**
     * 0-开课 1-未开课
     */
    private Integer status;


}
