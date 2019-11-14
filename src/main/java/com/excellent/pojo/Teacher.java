package com.excellent.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
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
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

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
