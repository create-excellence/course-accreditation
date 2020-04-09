package com.excellent.accreditation.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: ashe
 * @DATE: 2020/4/7 22:08
 */
@Data
public class CourseEvaluationStudentVo {

    String sno;

    String name;

    //学生是否已评价
    private Boolean isEvaluation;

    LocalDateTime submitTime;
}
