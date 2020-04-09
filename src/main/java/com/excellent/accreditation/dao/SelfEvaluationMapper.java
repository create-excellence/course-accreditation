package com.excellent.accreditation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.excellent.accreditation.model.entity.SelfEvaluation;
import com.excellent.accreditation.model.vo.CourseEvaluationStudentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author evildoer
 */
@Repository
public interface SelfEvaluationMapper extends BaseMapper<SelfEvaluation> {

    List<CourseEvaluationStudentVo> selectCourseEvaluationStudent(@Param("student") String student, @Param("courseEvaluationId")Integer courseEvaluationId);
}
