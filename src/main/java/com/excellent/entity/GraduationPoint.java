package com.excellent.entity;

import com.excellent.common.BaseEntity;
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
public class GraduationPoint extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String no;

    private Integer graduationDemandId;

    private String content;


}
