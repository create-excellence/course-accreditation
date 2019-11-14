package com.excellent.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class SelfEvaluation implements Serializable {

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

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
