package com.excellent.accreditation.model.entity;

import com.excellent.accreditation.common.domain.BaseEntity;
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
public class Questionnaire extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer courseClassId;

    private Integer totalSocre;

    private String describe;


}
