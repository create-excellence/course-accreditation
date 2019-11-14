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
public class GraduationPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String no;

    private Integer graduationDemandId;

    private String content;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
