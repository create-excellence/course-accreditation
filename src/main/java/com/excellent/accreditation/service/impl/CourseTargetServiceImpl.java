package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.dao.CourseTargetMapper;
import com.excellent.accreditation.model.entity.CourseTarget;
import com.excellent.accreditation.service.ICourseTargetService;
import org.springframework.stereotype.Service;

/**
 * @Author evildoer
 */
@Service
public class CourseTargetServiceImpl extends ServiceImpl<CourseTargetMapper, CourseTarget> implements ICourseTargetService {

    @Override
    public void checkCourseTarget(Integer courseTargetId) {
        if (this.getById(courseTargetId) == null) {
            throw new ConflictException("课程目标不存在");
        }
    }
}
