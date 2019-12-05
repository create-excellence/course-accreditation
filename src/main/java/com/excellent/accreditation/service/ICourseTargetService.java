package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.model.entity.CourseTarget;

/**
 * @Author evildoer
 */
public interface ICourseTargetService extends IService<CourseTarget> {

    void checkCourseTarget(Integer courseTargetId);
}
