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
public class SelfEvaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
