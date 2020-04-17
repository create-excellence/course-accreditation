package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.*;
import com.excellent.accreditation.dao.SelectCourseMapper;
import com.excellent.accreditation.model.entity.Course;
import com.excellent.accreditation.model.entity.CourseClass;
import com.excellent.accreditation.model.entity.SelectCourse;
import com.excellent.accreditation.model.entity.Student;
import com.excellent.accreditation.model.form.CourseEvaluationStudentQuery;
import com.excellent.accreditation.model.form.SelectCourseQuery;
import com.excellent.accreditation.model.vo.SelectCourseVo;
import com.excellent.accreditation.service.ICourseClassService;
import com.excellent.accreditation.service.ISelectCourseService;
import com.excellent.accreditation.service.IStudentService;
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
import java.util.stream.Collectors;

/**
 * @Author evildoer
 */
@Service
public class SelectCourseServiceImpl extends ServiceImpl<SelectCourseMapper, SelectCourse> implements ISelectCourseService {


    @Autowired
    SelectCourseMapper selectCourseMapper;

    private final ICourseClassService courseClassService;

    private final IStudentService studentService;

    public SelectCourseServiceImpl(ICourseClassService courseClassService, IStudentService studentService) {
        this.courseClassService = courseClassService;
        this.studentService = studentService;
    }

    @Override
    public boolean create(SelectCourse selectCourse) {
        this.check(selectCourse, Const.CREATE);
        selectCourse.setCreateTime(LocalDateTime.now());
        selectCourse.setUpdateTime(LocalDateTime.now());
        boolean result = this.save(selectCourse);
        // 操作成功
        if (result)
            return true;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }


    @Override
    public List<ExcelResult> saveBachByExcel(MultipartFile file) {
        List<Map<Integer, String>> list = ExcelUtils.readExcelGetList(file);
        List<ExcelResult> excelResults = new ArrayList<>();
        list.forEach(data -> {
            ExcelResult excelResult = new ExcelResult();
            try {
                EmptyCheckUtils.checkExcelMapAndSetNo(data, excelResult, 2);
                CourseClass courseClass = courseClassService.getByNo(data.get(1));
                Student student = studentService.getByCode(data.get(2));
                if(courseClass==null){
                    throw new ConflictException("开课班级不存在");
                }
                if(student==null){
                    throw new ConflictException("学生不存在");
                }
                SelectCourse selectCourse = new SelectCourse();
                selectCourse.setCourseClassId(courseClass.getId());
                selectCourse.setStudentId(student.getId());
                if (this.create(selectCourse)) {
                    excelResult.setStatus(Const.SUCCESS_INCREASE);
                    excelResult.setMessage("添加成功");
                }
            } catch (NumberFormatException e) {
                excelResult.setMessage("无法将部分字段转为数字类型");
            } catch (UniqueException | ExcelException e) {
                excelResult.setMessage(e.getMessage());
            }
            excelResults.add(excelResult);
        });
        return excelResults;
    }

    @Override
    public void check(SelectCourse selectCourse, Integer checkType) {
        if (checkType.equals(Const.CREATE) || selectCourse.getCourseClassId() != null) {
            courseClassService.checkCourseClass(selectCourse.getCourseClassId());
        }
        if (checkType.equals(Const.CREATE) || selectCourse.getStudentId() != null) {
            studentService.checkStudent(selectCourse.getStudentId());
        }
        this.checkUnique(selectCourse);
    }

    private void checkUnique(SelectCourse selectCourse) {
        if (selectCourse.getStudentId() == null && selectCourse.getCourseClassId() == null) {
            return;
        }
        if (selectCourse.getCourseClassId() == null && selectCourse.getStudentId() != null) {
            throw new EmptyException("课程不能为空");
        }
        if (selectCourse.getCourseClassId() != null && selectCourse.getStudentId() == null) {
            throw new EmptyException("学号不能为空");
        }
        LambdaQueryWrapper<SelectCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SelectCourse::getCourseClassId, selectCourse.getCourseClassId());
        queryWrapper.eq(SelectCourse::getStudentId, selectCourse.getStudentId());
        SelectCourse selectCourse1 = this.getOne(queryWrapper);
        if (selectCourse1 != null && !selectCourse1.getId().equals(selectCourse.getId())) {
            throw new UniqueException("已存在相同记录");
        }
    }

    @Override
    public void checkSelectCourse(Integer courseClassId, Integer studentId) {
        LambdaQueryWrapper<SelectCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SelectCourse::getCourseClassId, courseClassId);
        queryWrapper.eq(SelectCourse::getStudentId, studentId);
        if (this.getOne(queryWrapper) != null) {
            throw new UniqueException("选课已存在");
        }
    }

    @Override
    public SelectCourseVo selectCourseId(Integer id) {
        return selectCourseMapper.querySelectCourseById(id);
    }

    /**
     * @author pu
     * @data 2019/12/10
     * description:
     */
    @Override
    public PageInfo<SelectCourseVo> pageByQuery(SelectCourseQuery selectCourseQuery) {
        PageHelper.startPage(selectCourseQuery.getPage(), selectCourseQuery.getPageSize());
        List<SelectCourseVo> list = selectCourseMapper.pageByQuery(selectCourseQuery.getStudent(), selectCourseQuery.getTeacher(), selectCourseQuery.getSemester(), selectCourseQuery.getCourse());
        return new PageInfo<>(list);
    }


    /**
     * @Author 安羽兮
     * @Description //TODO
     * @Date 13:03 2020/3/17
     * @Param [selectCourseQuery]
     * @Return com.github.pagehelper.PageInfo<com.excellent.accreditation.model.vo.SelectCourseVo>
     **/
    @Override
    public PageInfo<SelectCourseVo> pageSelectByStudentId(SelectCourseQuery selectCourseQuery) {
        PageHelper.startPage(selectCourseQuery.getPage(), selectCourseQuery.getPageSize());
        List<SelectCourseVo> list = selectCourseMapper.pageSelectByStudentId(selectCourseQuery.getStudentId(), selectCourseQuery.getTeacher(), selectCourseQuery.getSemester(), selectCourseQuery.getCourse());
        return new PageInfo<>(list);
    }

    @Override
    public Integer countClassStudent(Integer courseClassId) {
        LambdaQueryWrapper<SelectCourse> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SelectCourse::getCourseClassId,courseClassId);
        return  baseMapper.selectCount(queryWrapper);
    }

    @Override
    public List<Student> selectClassStudent(CourseEvaluationStudentQuery query) {
        LambdaQueryWrapper<SelectCourse> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SelectCourse::getCourseClassId,query.getCourseEvaluationId());
        List<Integer> studentIdList =  this.list(queryWrapper).stream().map(SelectCourse::getStudentId).collect(Collectors.toList());
        LambdaQueryWrapper<Student> studentQueryWrapper=new LambdaQueryWrapper<>();
        studentQueryWrapper.in(Student::getId,studentIdList);
        if(StringUtils.isNotEmpty(query.getStudent())){
            studentQueryWrapper.like(Student::getName,query.getStudent());
        }
        PageHelper.startPage(query.getPage(), query.getPageSize());
        return studentService.list(studentQueryWrapper);
    }
}
