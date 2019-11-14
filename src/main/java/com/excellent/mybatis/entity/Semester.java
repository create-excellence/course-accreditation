package com.excellent.mybatis.entity;

import com.excellent.common.BaseEntity;
import java.time.LocalDate;
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
public class Semester extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private LocalDate startTime;


}
