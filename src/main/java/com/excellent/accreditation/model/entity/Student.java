package com.excellent.accreditation.model.entity;

import com.excellent.accreditation.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String sno;

    private String password;

    private String name;

    private String sex;

    private String grade;

    private Integer majorId;

    /**
     * 正常-0
     */
    private Integer status;

    private LocalDateTime loginTime;


}
