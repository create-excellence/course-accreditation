package com.excellent.accreditation.model.entity;

import com.excellent.accreditation.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author evildoer
 * @since 2020-03-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SelfEvaluation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer studentId;

    private Integer courseTargetId;

    private Integer courseEvaluationId;

    /**
     * 评价档次
     */
    private String answer;

    /**
     * 得分
     */
    private Integer score;

    public SelfEvaluation(){
        this.setUpdateTime(LocalDateTime.now());
    }


}
