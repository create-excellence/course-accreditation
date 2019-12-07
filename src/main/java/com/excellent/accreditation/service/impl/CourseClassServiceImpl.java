package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.UniqueException;
import com.excellent.accreditation.dao.CourseClassMapper;
import com.excellent.accreditation.model.entity.Course;
import com.excellent.accreditation.model.entity.CourseClass;
import com.excellent.accreditation.model.form.CourseClassQuery;
import com.excellent.accreditation.service.ICourseClassService;
import com.excellent.accreditation.service.ICourseService;
import com.excellent.accreditation.service.ISemesterService;
import com.excellent.accreditation.service.ITeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author evildoer
 */
@Service
public class CourseClassServiceImpl extends ServiceImpl<CourseClassMapper, CourseClass> implements ICourseClassService {

    private final ITeacherService teacherService;

    private final ISemesterService semesterService;

    private final ICourseService courseService;

    @Autowired
    public CourseClassServiceImpl(ITeacherService teacherService,ISemesterService semesterService, ICourseService courseService) {
        this.teacherService = teacherService;
        this.semesterService = semesterService;
        this.courseService = courseService;
    }


    @Override
    public void checkNo(String no) {
        LambdaQueryWrapper<CourseClass> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseClass::getNo, no);
        if (super.getOne(queryWrapper) != null) {
            throw new UniqueException("课程编号不能重复");
        }
    }

    @Override
    public PageInfo<CourseClass> pageByQuery(CourseClassQuery query) {
        LambdaQueryWrapper<CourseClass> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(query.getNo()))
            queryWrapper.like(CourseClass::getNo, query.getNo());
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<CourseClass> list = this.list(queryWrapper);
        return new PageInfo<>(list);
    }

    @Override
    public boolean create(CourseClass courseClass) {
        this.check(courseClass,Const.CREATE);
        courseClass.setCreateTime(LocalDateTime.now());
        courseClass.setUpdateTime(LocalDateTime.now());
        if (this.save(courseClass)){
            return true;
        }
        throw new DatabaseException("未知异常, 数据库操作失败");
    }



    @Override
    public List<ExcelResult> saveBachByExcel(MultipartFile file) {
        return null;
    }

    @Override
    public void check (CourseClass courseClass,Integer type){
        if(type.equals(Const.CREATE)||courseClass.getNo()!=null){
            this.checkNo(courseClass.getNo());
        }
        if(type.equals(Const.CREATE)||courseClass.getTeacherId()!=null){
            teacherService.checkTeacher(courseClass.getTeacherId());
        }
        if(type.equals(Const.CREATE)||courseClass.getSemesterId()!=null){
            semesterService.checkSemester(courseClass.getSemesterId());
        }
        if(type.equals(Const.CREATE)||courseClass.getCourseId()!=null){
            courseService.checkCourse(courseClass.getCourseId());
        }

    }
}
