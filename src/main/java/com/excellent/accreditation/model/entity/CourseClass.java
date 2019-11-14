package com.excellent.accreditation.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 *
 * @Author evildoer
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
