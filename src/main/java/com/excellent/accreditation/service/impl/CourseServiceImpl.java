package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.dao.CourseMapper;
import com.excellent.accreditation.model.entity.Course;
import com.excellent.accreditation.service.ICourseService;
import org.springframework.stereotype.Service;


/**
 * @Author evildoer
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    /**
    *@Description: 添加课程,校验是否存在课程代码
    *@Param: [course]
    *@return: boolean
    *@Author: ashe
    *@date: 2019/12/3
    */
    @Override
    public ServerResponse creatCourse(Course course) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code",course.getCode());
        if(super.getOne(queryWrapper)!=null){
           return  ServerResponse.createByErrorMessage("课程代码不能重复");
        }
        if (super.save(course)){
            return ServerResponse.createBySuccessMessage("添加课程成功");
        }
        return ServerResponse.createByErrorMessage("添加课程失败");
    }

}
