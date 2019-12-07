package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.model.entity.Course;
import com.excellent.accreditation.model.form.CourseQuery;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author evildoer
 */
public interface ICourseService extends IService<Course> {

    void checkCode(String code,Integer courseId);

    PageInfo<Course> pageByQuery(CourseQuery query);

    boolean create(Course course);

    List<ExcelResult> saveBachByExcel(MultipartFile file);

    void checkCourse(Integer courseId);
}
