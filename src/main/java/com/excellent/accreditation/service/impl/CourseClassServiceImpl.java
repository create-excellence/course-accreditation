package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.UniqueException;
import com.excellent.accreditation.dao.CourseClassMapper;
import com.excellent.accreditation.model.entity.CourseClass;
import com.excellent.accreditation.model.form.CourseClassQuery;
import com.excellent.accreditation.model.vo.CourseClassVo;
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

    private final CourseClassMapper courseClassMapper;

    private final ITeacherService teacherService;

    private final ISemesterService semesterService;

    private final ICourseService courseService;

    @Autowired
    public CourseClassServiceImpl(CourseClassMapper courseClassMapper, ITeacherService teacherService, ISemesterService semesterService, ICourseService courseService) {
        this.courseClassMapper = courseClassMapper;
        this.teacherService = teacherService;
        this.semesterService = semesterService;
        this.courseService = courseService;
    }


    @Override
    public void checkNo(String no, Integer courseClassId) {
        LambdaQueryWrapper<CourseClass> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseClass::getNo, no);
        CourseClass courseClass = super.getOne(queryWrapper);
        if (courseClass != null && !courseClass.getCourseId().equals(courseClassId)) {
            // 如果code已存在还要检查是否当前更新的是否为同一条记录,若不同则抛出异常
            throw new UniqueException("课程编号不能重复");
        }
    }

    @Override
    public PageInfo<CourseClassVo> pageByQuery(CourseClassQuery query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<CourseClassVo> list = courseClassMapper.pageByQuery(query.getCourse(), query.getTeacher(), query.getSemester(),query.getCourseId());
        return new PageInfo<>(list);
    }

    @Override
    public boolean create(CourseClass courseClass) {
        this.check(courseClass, Const.CREATE);
        courseClass.setUpdateTime(LocalDateTime.now());
        courseClass.setCreateTime(LocalDateTime.now());
        if (this.save(courseClass)) {
            return true;
        }
        throw new DatabaseException("未知异常, 数据库操作失败");
    }

    public CourseClassVo queryCourseClassById(Integer id) {
        return courseClassMapper.queryCourseClassById(id);
    }

    @Override
    public List<ExcelResult> saveBachByExcel(MultipartFile file) {
        return null;
    }

    @Override
    public void check(CourseClass courseClass, Integer type) {
        if (type.equals(Const.CREATE) || courseClass.getNo() != null) {
            this.checkNo(courseClass.getNo(), courseClass.getId());
        }
        if (type.equals(Const.CREATE) || courseClass.getTeacherId() != null) {
            teacherService.checkTeacher(courseClass.getTeacherId());
        }
        if (type.equals(Const.CREATE) || courseClass.getSemesterId() != null) {
            semesterService.checkSemester(courseClass.getSemesterId());
        }
        if (type.equals(Const.CREATE) || courseClass.getCourseId() != null) {
            courseService.checkCourse(courseClass.getCourseId());
        }

    }

    /**
     * @Author 安羽兮
     * @Description //TODO
     * @Date 16:03 2020/3/17
     * @Param [courseClassQuery, studentId]
     * @Return com.github.pagehelper.PageInfo<com.excellent.accreditation.model.entity.Course>
     **/
    @Override
    public PageInfo<CourseClassVo> pageSelectByStudentId(CourseClassQuery query, Integer studentId) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<CourseClassVo> list = courseClassMapper.pageSelectByStudentId(studentId, query.getCourse(), query.getTeacher(), query.getSemester());
        return new PageInfo<>(list);
    }

    @Override
    public void checkCourseClass(Integer courseClassId) {
        if (this.getById(courseClassId) == null) {
            throw new ConflictException("课程不存在");
        }
    }
}
