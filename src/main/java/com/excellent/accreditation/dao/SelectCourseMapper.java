package com.excellent.accreditation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.excellent.accreditation.model.entity.SelectCourse;
import com.excellent.accreditation.model.vo.SelectCourseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pu
 * @data 2019/12/10
 * description:
 */
@Repository
public interface SelectCourseMapper extends BaseMapper<SelectCourse> {

    List<SelectCourseVo> pageByQuery(@Param("student") String student, @Param("teacher") String teacher, @Param("semester") String semester, @Param("course") String course);

    SelectCourseVo querySelectCourseById(Integer id);

    List<SelectCourseVo> pageSelectByStudentId(@Param("studentId") Integer studentId, @Param("teacher") String teacher, @Param("semester") String semester, @Param("course") String course);
}
