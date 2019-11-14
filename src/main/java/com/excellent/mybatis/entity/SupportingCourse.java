package com.excellent.mybatis.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SupportingCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer graduationPointId;

    private Integer couseId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
