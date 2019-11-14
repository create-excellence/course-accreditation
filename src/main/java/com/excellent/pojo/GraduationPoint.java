package com.excellent.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class GraduationPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    private String no;

    private Integer graduationDemandId;

    private String content;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
