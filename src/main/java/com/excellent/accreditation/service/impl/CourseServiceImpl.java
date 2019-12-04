package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.UniqueException;
import com.excellent.accreditation.dao.CourseMapper;
import com.excellent.accreditation.model.entity.Course;
import com.excellent.accreditation.model.form.CourseQuery;
import com.excellent.accreditation.service.ICourseService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * @Author evildoer
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    /**
     * @Description: 添加课程, 校验是否存在课程代码
     * @Param: [course]
     * @return: boolean
     * @Author: ashe
     * @date: 2019/12/3
     */
    public boolean create(Course course) {
        course.setCreateTime(LocalDateTime.now());
        course.setUpdateTime(LocalDateTime.now());
        this.checkCode(course.getCode());                  // 课程代码必须唯一
        boolean result = this.save(course);
        // 操作成功
        if (result)
            return result;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }


    /**
     * @Description: 检查课程code唯一
     * @Param: [code]
     * @return: void
     * @Author: ashe
     * @date: 2019/12/3
     */
    @Override
    public void checkCode(@NonNull String code) {
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getCode, code);
        if (super.getOne(queryWrapper) != null) {
            throw new UniqueException("课程代码不能重复");
        }
    }

    @Override
    public IPage<Course> pageByQuery(@NonNull CourseQuery courseQuery) {
        Page<Course> page = new Page<>(courseQuery.getCurrent(), courseQuery.getSize());
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(courseQuery.getCode()))
            queryWrapper.like(Course::getCode, courseQuery.getCode());
        if (StringUtils.isNotEmpty(courseQuery.getName()))
            queryWrapper.like(Course::getName, courseQuery.getName());
        if (StringUtils.isNotEmpty(courseQuery.getNature()))
            queryWrapper.eq(Course::getNature, courseQuery.getNature());
        return this.page(page, queryWrapper);
    }
}
