package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.UniqueException;
import com.excellent.accreditation.dao.StudentMapper;
import com.excellent.accreditation.model.entity.Student;
import com.excellent.accreditation.model.form.StudentQuery;
import com.excellent.accreditation.service.IMajorService;
import com.excellent.accreditation.service.IStudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public PageInfo<Student> pageByQuery(StudentQuery query) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(query.getSno()))
            queryWrapper.like(Student::getSno, query.getSno());
        if (StringUtils.isNotEmpty(query.getName()))
            queryWrapper.like(Student::getName, query.getName());
        PageHelper.startPage(query.getCurrent(), query.getSize());
        List<Student> list = this.list(queryWrapper);
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        return pageInfo;
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
