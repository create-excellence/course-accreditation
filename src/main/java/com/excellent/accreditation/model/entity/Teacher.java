package com.excellent.accreditation.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 
 *
 * @Author evildoer
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 工号
     */
    private String jno;

    private String password;

    /**
     * 角色
     */
    private String role;

    private String name;

    private String sex;

    /**
     * 职称
     */
    private String title;

    private LocalDate birth;

    private String graduateSchool;

    private String graduateMajor;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime loginTime;


}
