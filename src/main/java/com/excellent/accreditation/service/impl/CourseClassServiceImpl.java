package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.CommonException;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.UniqueException;
import com.excellent.accreditation.dao.CourseClassMapper;
import com.excellent.accreditation.manage.UserManage;
import com.excellent.accreditation.model.entity.Course;
import com.excellent.accreditation.model.entity.CourseClass;
import com.excellent.accreditation.model.entity.Semester;
import com.excellent.accreditation.model.entity.Teacher;
import com.excellent.accreditation.model.form.CourseClassQuery;
import com.excellent.accreditation.model.vo.CourseClassVo;
import com.excellent.accreditation.service.ICourseClassService;
import com.excellent.accreditation.service.ICourseService;
import com.excellent.accreditation.service.ISemesterService;
import com.excellent.accreditation.service.ITeacherService;
import com.excellent.accreditation.untils.EmptyCheckUtils;
import com.excellent.accreditation.untils.ExcelUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author evildoer
 */
@Service
public class CourseClassServiceImpl extends ServiceImpl<CourseClassMapper, CourseClass> implements ICourseClassService {

    private final CourseClassMapper courseClassMapper;

    private final ITeacherService teacherService;

    private final ISemesterService semesterService;

    private final ICourseService courseService;

    private final UserManage userManage;

    @Autowired
    public CourseClassServiceImpl(CourseClassMapper courseClassMapper, ITeacherService teacherService, ISemesterService semesterService, ICourseService courseService, UserManage userManage) {
        this.courseClassMapper = courseClassMapper;
        this.teacherService = teacherService;
        this.semesterService = semesterService;
        this.courseService = courseService;
        this.userManage = userManage;
    }


    @Override
    public void checkNo(String no, Integer courseClassId) {
        CourseClass courseClass =this.getByNo(no);
        if (courseClass != null && !courseClass.getId().equals(courseClassId)) {
            // 如果code已存在还要检查是否当前更新的是否为同一条记录,若不同则抛出异常
            throw new UniqueException("课程编号不能重复");
        }
    }

    @Override
    public CourseClass getByNo(String no) {
        LambdaQueryWrapper<CourseClass> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseClass::getNo, no);
        return this.getOne(queryWrapper);
    }

    @Override
    public PageInfo<CourseClassVo> pageByQuery(CourseClassQuery query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<CourseClassVo> list = courseClassMapper.pageByQuery(query.getCourse(), query.getTeacher(), query.getSemester(), query.getCourseId(), null, query.getSemesterId());
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<CourseClassVo> getMyCourse(CourseClassQuery query) {
        List<String> roles = userManage.getRolesByCode(userManage.getCodeByToken());
        for (String role : roles) {
            if (role.equals(Const.TEACHER)) {
                PageHelper.startPage(query.getPage(), query.getPageSize());
                List<CourseClassVo> list = courseClassMapper.pageByQuery(query.getCourse(), null, null, null, userManage.getUserInfo().getId(), query.getSemesterId());
                return new PageInfo<>(list);
            }
            if (role.equals(Const.STUDENT)) {
                PageHelper.startPage(query.getPage(), query.getPageSize());
                List<CourseClassVo> list = courseClassMapper.pageByQueryStudent(query.getCourse(), null, null, null, userManage.getUserInfo().getId(), query.getSemesterId());
                return new PageInfo<>(list);
            }
        }
        return null;
    }

    @Override
    public boolean create(CourseClass courseClass) {
        this.check(courseClass, Const.CREATE);
        courseClass.setUpdateTime(LocalDateTime.now());
        courseClass.setCreateTime(LocalDateTime.now());
        courseClass.setStatus(0);
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
        List<Map<Integer, String>> list= ExcelUtils.readExcelGetList(file);
        List<ExcelResult> excelResults = new ArrayList<>();
        list.forEach(data -> {
            ExcelResult excelResult = new ExcelResult();
            try {
                EmptyCheckUtils.checkExcelMapAndSetNo(data,excelResult, 3);
                String no = data.get(1);
                Course course = courseService.getByCode(data.get(2));
                Teacher teacher =teacherService.getByCode(data.get(3));
                Semester semester=semesterService.getByName(data.get(4));
                Integer startWeek = Integer.valueOf(data.get(5));
                Integer endWeek = Integer.valueOf(data.get(6));
                if(course==null){
                    throw new ConflictException("课程不存在");
                }
                if(teacher==null){
                    throw new ConflictException("老师不存在");
                }
                if(semester==null){
                    throw new ConflictException("对应的学期不存在");
                }
               CourseClass courseClass =new CourseClass();
                courseClass.setNo(no);
                courseClass.setCourseId(course.getId());
                courseClass.setTeacherId(teacher.getId());
                courseClass.setSemesterId(semester.getId());
                courseClass.setStartWeek(startWeek);
                courseClass.setEndWeek(endWeek);
                if (this.create(courseClass)) {
                    excelResult.setStatus(Const.SUCCESS_INCREASE);
                    excelResult.setMessage("添加成功");
                }
            } catch (NumberFormatException e) {
                excelResult.setMessage("无法将部分字段转为数字类型");
            } catch (CommonException e) {
                excelResult.setMessage(e.getMessage());
            }
            excelResults.add(excelResult);
        });
        return excelResults;
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
