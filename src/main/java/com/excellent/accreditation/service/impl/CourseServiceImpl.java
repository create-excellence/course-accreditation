package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.ExcelException;
import com.excellent.accreditation.common.exception.UniqueException;
import com.excellent.accreditation.dao.CourseMapper;
import com.excellent.accreditation.model.entity.Course;
import com.excellent.accreditation.model.form.CourseQuery;
import com.excellent.accreditation.service.ICourseService;
import com.excellent.accreditation.untils.EmptyCheckUtils;
import com.excellent.accreditation.untils.ExcelUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
        this.checkCode(course.getCode(),null);                  // 课程代码必须唯一
        boolean result = this.save(course);
        // 操作成功
        if (result)
            return true;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }


    @Override
    public List<ExcelResult> saveBachByExcel(MultipartFile file) {
        List<Map<Integer, String>> list=ExcelUtils.readExcelGetList(file);
        List<ExcelResult> excelResults = new ArrayList<>();
        list.forEach(data -> {
            ExcelResult excelResult = new ExcelResult();
            try {
                EmptyCheckUtils.checkExcelMapAndSetNo(data,excelResult, 4);
                String name = data.get(1);
                String code = data.get(2);
                Double credit = Double.parseDouble(data.get(3));
                String nature = data.get(4);
                Course course = new Course(name, code, credit, nature);
                if (this.create(course)) {
                    excelResult.setStatus(Const.SUCCESS_INCREASE);
                    excelResult.setMessage("添加成功");
                }
            } catch (NumberFormatException e) {
                excelResult.setMessage("无法将部分字段转为数字类型");
            } catch (UniqueException | ExcelException | DatabaseException e) {
                excelResult.setMessage(e.getMessage());
            }
            excelResults.add(excelResult);
        });
        return excelResults;
    }

    @Override
    public void checkCourse(Integer courseId) {
        if (this.getById(courseId) == null) {
            throw new ConflictException("课程不存在");
        }
    }


    /**
     * @Description: 检查课程code唯一
     * @Param: [code]
     * @return: void
     * @Author: ashe
     * @date: 2019/12/3
     */
    @Override
    public void checkCode(@NonNull String code,Integer courseId) {
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getCode, code);
        Course course =super.getOne(queryWrapper);
        if ( course!= null && !course.getId().equals(courseId)) {
            // 如果code已存在还要检查是否当前更新的是否为同一条记录,若不同则抛出异常
            throw new UniqueException("课程代码不能重复");
        }
    }

    @Override
    public PageInfo<Course> pageByQuery(@NonNull CourseQuery query) {
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(query.getCode()))
            queryWrapper.like(Course::getCode, query.getCode());
        if (StringUtils.isNotEmpty(query.getName()))
            queryWrapper.like(Course::getName, query.getName());
        if (StringUtils.isNotEmpty(query.getNature()))
            queryWrapper.eq(Course::getNature, query.getNature());
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<Course> list = this.list(queryWrapper);
        PageInfo<Course> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
