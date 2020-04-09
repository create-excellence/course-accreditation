package com.excellent.accreditation.model.vo;

import com.excellent.accreditation.model.entity.CourseEvaluation;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @description:
 * @author: ashe
 * @DATE: 2020/3/30 17:43
 */
@Data
public class CourseEvaluationVo extends CourseEvaluation {

    private String course;

    private String questionnaire;

    // 学生是否评价
    private Boolean isEvaluation;

    // 学生评价人数
    private Integer count;


    // 班级人数
    private Integer courseClassCount;


    public CourseEvaluationVo(){
    }

    public CourseEvaluationVo(CourseEvaluation courseEvaluation) {
        BeanUtils.copyProperties(courseEvaluation, this);
    }

}
