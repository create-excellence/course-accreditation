package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.model.entity.Course;
import com.excellent.accreditation.model.form.CourseQuery;

/**
 * @Author evildoer
 */
public interface ICourseService extends IService<Course> {

    void checkCode(String code);

    IPage<Course> pageByQuery(CourseQuery courseQuery);

    boolean create(Course course);
}
