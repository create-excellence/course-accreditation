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
public class GraduationDemand implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer majorId;

    /**
     * 毕业要求编号
     */
    private String no;

    /**
     * 必要要求内容
     */
    private String content;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
