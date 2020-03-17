package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.model.entity.CourseClass;
import com.excellent.accreditation.model.form.CourseClassQuery;
import com.excellent.accreditation.model.vo.CourseClassVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author evildoer
 */
public interface ICourseClassService extends IService<CourseClass> {

    void checkNo(String No, Integer courseClassId);

    PageInfo<CourseClassVo> pageByQuery(CourseClassQuery query);

    boolean create(CourseClass courseClass);

    CourseClassVo queryCourseClassById(Integer id);

    List<ExcelResult> saveBachByExcel(MultipartFile file);

    void check(CourseClass courseClass, Integer type);

    void checkCourseClass(Integer courseClassId);

    PageInfo<CourseClassVo> pageSelectByStudentId(CourseClassQuery courseClassQuery, Integer studentId);
}
