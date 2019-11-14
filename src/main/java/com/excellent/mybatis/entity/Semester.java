package com.excellent.mybatis.entity;

import java.time.LocalDate;
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
public class Semester implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private LocalDate startTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
