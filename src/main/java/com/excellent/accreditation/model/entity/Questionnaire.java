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
public class Questionnaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer courseClassId;

    private Integer totalSocre;

    private String describe;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
