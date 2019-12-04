package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.UniqueException;
import com.excellent.accreditation.dao.StudentMapper;
import com.excellent.accreditation.model.entity.Student;
import com.excellent.accreditation.model.form.StudentQuery;
import com.excellent.accreditation.service.IMajorService;
import com.excellent.accreditation.service.IStudentService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public IPage<Student> pageByQuery(StudentQuery studentQuery) {
        Page<Student> page = new Page<>(studentQuery.getCurrent(), studentQuery.getSize());
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(studentQuery.getSno()))
            queryWrapper.like(Student::getSno, studentQuery.getSno());
        if (StringUtils.isNotEmpty(studentQuery.getName()))
            queryWrapper.like(Student::getName, studentQuery.getName());
        return this.page(page, queryWrapper);

    }

    @Override
    public boolean create(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        this.checkCode(student.getSno());                   // 学号必须唯一
        majorService.checkMajor(student.getMajorId());     // 查看对应专业是否存在
        boolean result = this.save(student);
        // 操作成功
        if (result)
            return result;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }
}
