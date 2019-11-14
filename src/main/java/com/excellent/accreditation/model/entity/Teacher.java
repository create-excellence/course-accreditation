package com.excellent.accreditation.model.entity;

import com.excellent.accreditation.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Teacher extends BaseEntity {

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

    private LocalDateTime loginTime;


}
