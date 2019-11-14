package com.excellent.entity;

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
public class SelfEvaluation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer studentId;

    private Integer courseTargetId;

    /**
     * 评价档次
     */
    private String answer;

    /**
     * 得分
     */
    private Integer score;


}
