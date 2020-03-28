package com.excellent.accreditation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.excellent.accreditation.model.entity.Questionnaire;
import com.excellent.accreditation.model.form.QuestionnaireQuery;
import com.excellent.accreditation.model.vo.QuestionnaireVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ashe
 * @since 2019-11-29
 */
@Repository
public interface QuestionnaireMapper extends BaseMapper<Questionnaire> {

    List<QuestionnaireVo> pageByQuery(@Param("totalScore") Integer totalScore, @Param("name") String name, @Param("courseClassId") Integer courseClassId,@Param("teacherId") Integer teacherId);

}
