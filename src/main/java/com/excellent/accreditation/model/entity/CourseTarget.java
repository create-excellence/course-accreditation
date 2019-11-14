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
public class CourseTarget implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer questionnaireId;

    private String title;

    /**
     * 指标点序号
     */
    private Integer pointId;

    /**
     * 选项
     */
    private String options;

    /**
     * 描述
     */
    private String describe;

    /**
     * 选项得分
     */
    private String optionsScore;

    /**
     * 总分
     */
    private Integer totalScore;

    /**
     * 序号
     */
    private Integer sequence;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
