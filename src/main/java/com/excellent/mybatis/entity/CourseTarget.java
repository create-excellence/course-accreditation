package com.excellent.mybatis.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseTarget implements Serializable {

    private static final long serialVersionUID = 1L;

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
