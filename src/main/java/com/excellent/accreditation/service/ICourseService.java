package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.model.entity.Course;

/**
 * @Author evildoer
 */
public interface ICourseService extends IService<Course> {

     ServerResponse creatCourse(Course course);


}
