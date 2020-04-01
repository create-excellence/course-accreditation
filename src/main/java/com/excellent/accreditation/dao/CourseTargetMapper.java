package com.excellent.accreditation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.excellent.accreditation.model.entity.CourseTarget;
import com.excellent.accreditation.model.entity.GraduationPoint;
import com.excellent.accreditation.model.entity.Questionnaire;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author evildoer
 */
@Repository
public interface CourseTargetMapper extends BaseMapper<CourseTarget> {

    List<GraduationPoint> point (@Param("questionnaireId") Integer questionnaireId);

}
