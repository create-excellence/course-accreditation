package com.excellent.accreditation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.excellent.accreditation.model.entity.SupportingCourse;
import com.excellent.accreditation.model.vo.SupportingCourseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author evildoer
 */
@Repository
public interface SupportingCourseMapper extends BaseMapper<SupportingCourse> {

    SupportingCourseVo getVoById(Integer id);

    List<SupportingCourseVo> pageByQuery(@Param("course") String course, @Param("graduationPointContent") String graduationPointContent, @Param("graduationPointId") Integer graduationPointId);
}
