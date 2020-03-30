package com.excellent.accreditation.model.entity;

import com.excellent.accreditation.common.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author evildoer
 * @since 2020-03-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CourseEvaluation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer courseClassId;

    private Integer questionnaireId;

    private Integer status;

    private String describes;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;


}
