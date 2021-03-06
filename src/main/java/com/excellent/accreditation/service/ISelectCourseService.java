package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.model.entity.SelectCourse;
import com.excellent.accreditation.model.entity.Student;
import com.excellent.accreditation.model.form.CourseEvaluationStudentQuery;
import com.excellent.accreditation.model.form.SelectCourseQuery;
import com.excellent.accreditation.model.vo.SelectCourseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author evildoer
 */
public interface ISelectCourseService extends IService<SelectCourse> {

    PageInfo<SelectCourseVo> pageByQuery(SelectCourseQuery selectCourseQuery);

    boolean create(SelectCourse selectCourse);

    List<ExcelResult> saveBachByExcel(MultipartFile file);

    void check(SelectCourse selectCourse, Integer checkType);

    SelectCourse getSelectCourse(Integer studentId, Integer courseClassId);

    SelectCourseVo selectCourseId(Integer id);

    PageInfo<SelectCourseVo> pageSelectByStudentId(SelectCourseQuery selectCourseQuery);

    Integer countClassStudent(Integer courseClassId);

    PageInfo<Student> selectClassStudent(CourseEvaluationStudentQuery query,Integer courseClassId);
}
