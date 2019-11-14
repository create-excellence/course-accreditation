package com.excellent.accreditation.model.entity;

import com.excellent.accreditation.common.BaseEntity;
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
public class SelectCourse extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 课程班级Id
     */
    private Integer courseClassId;

    /**
     * 学生Id
     */
    private Integer studentId;


}
