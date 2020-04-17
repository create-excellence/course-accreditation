package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.CommonException;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.UniqueException;
import com.excellent.accreditation.dao.StudentMapper;
import com.excellent.accreditation.model.entity.Major;
import com.excellent.accreditation.model.entity.Student;
import com.excellent.accreditation.model.form.StudentQuery;
import com.excellent.accreditation.model.vo.StudentVo;
import com.excellent.accreditation.service.IMajorService;
import com.excellent.accreditation.service.IStudentService;
import com.excellent.accreditation.untils.EmptyCheckUtils;
import com.excellent.accreditation.untils.ExcelUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.lang.NonNull;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    private final IMajorService majorService;

    public StudentServiceImpl(IMajorService majorService) {
        this.majorService = majorService;
    }

    @Override
    public void checkStudent(Integer studentId) {
        if (this.getById(studentId) == null) {
            throw new ConflictException("学生不存在");
        }
    }

    @Override
    public Student getByCode(String code) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getSno, code);       // 通过学号查询
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public void checkCode(@NonNull String code) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getSno, code);
        if (super.getOne(queryWrapper) != null) {
            throw new UniqueException("学号不能重复");
        }
    }

    @Override
    public PageInfo<StudentVo> pageByQuery(StudentQuery query) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(query.getSno()))
            queryWrapper.like(Student::getSno, query.getSno());
        if (StringUtils.isNotEmpty(query.getName()))
            queryWrapper.like(Student::getName, query.getName());
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<Student> list = this.list(queryWrapper);
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        PageInfo<StudentVo> result = new PageInfo<>(new ArrayList<>());
        pageInfo.getList().forEach(student -> {
            StudentVo studentVo = StudentVo.convert(student);
            Major major = majorService.getById(student.getMajorId());
            if (major != null) {
                studentVo.setMajor(major.getName());
            }
            result.getList().add(studentVo);
        });
        return result;
    }

    @Override
    public boolean create(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        student.setStatus(0);
        this.checkCode(student.getSno());                   // 学号必须唯一
        majorService.checkMajor(student.getMajorId());     // 查看对应专业是否存在
        boolean result = this.save(student);
        // 操作成功
        if (result)
            return result;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }

    @Override
    public List<ExcelResult> saveBachByExcel(MultipartFile file) {
        List<Map<Integer, String>> list = ExcelUtils.readExcelGetList(file);
        List<ExcelResult> excelResults = new ArrayList<>();
        list.forEach(data -> {
            ExcelResult excelResult = new ExcelResult();
            try {
                EmptyCheckUtils.checkExcelMapAndSetNo(data, excelResult, 6);
                String sno = data.get(1);
                String name = data.get(2);
                String password = data.get(3);
                String sex = data.get(4);
                String grade = data.get(5);
                Major major = majorService.getByCode(data.get(6));
                if(major==null){
                    throw new ConflictException("专业不存在");
                }
                Student student =new Student();
                student.setSno(sno);
                student.setName(name);
                student.setSex(sex);
                student.setPassword(password);
                student.setGrade(grade);
                student.setMajorId(major.getId());
                if (this.create(student)) {
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
    public boolean updatePassword(String code, String oldPassword, String newPassword) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getSno, code)
                .eq(Student::getPassword, oldPassword);
        Student student = this.baseMapper.selectOne(queryWrapper);
        if (student != null) {
            student.setPassword(newPassword);
            LambdaUpdateWrapper<Student> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Student::getSno, code);
            this.baseMapper.update(student, updateWrapper);
        }
        throw new ConflictException("密码错误，修改失败");
    }

    @Override
    public PageInfo<StudentVo> getStudentByCourseClassId(StudentQuery studentQuery) {
        List<StudentVo> list = this.baseMapper.queryByCourseClassId(studentQuery.getCourseClassId(),studentQuery.getSno(),studentQuery.getName());
        return new PageInfo<>(list);
    }
}
