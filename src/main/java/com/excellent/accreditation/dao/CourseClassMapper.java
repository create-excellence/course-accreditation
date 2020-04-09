package com.excellent.accreditation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.excellent.accreditation.model.entity.CourseClass;
import com.excellent.accreditation.model.vo.CourseClassVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author evildoer
 */
@Repository
public interface CourseClassMapper extends BaseMapper<CourseClass> {

    CourseClassVo queryCourseClassById(Integer id);

    List<CourseClassVo> pageByQuery(@Param("course") String course, @Param("teacher") String teacher, @Param("semester") String semester, @Param("courseId") Integer courseId, @Param("teacherId") Integer teacherId, @Param("semesterId") Integer semesterId);

    List<CourseClassVo> pageByQueryStudent(@Param("course") String course, @Param("teacher") String teacher, @Param("semester") String semester, @Param("courseId") Integer courseId, @Param("studentId") Integer studentId, @Param("semesterId") Integer semesterId);

    List<CourseClassVo> pageSelectByStudentId(@Param("studentId") Integer studentId, @Param("course") String course, @Param("teacher") String teacher, @Param("semester") String semester);
}
