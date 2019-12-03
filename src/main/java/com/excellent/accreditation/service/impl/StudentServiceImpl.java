package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.dao.StudentMapper;
import com.excellent.accreditation.model.entity.Student;
import com.excellent.accreditation.service.IStudentService;
import org.springframework.stereotype.Service;

/**
 * @Author evildoer
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Override
    public Student getByCode(String code) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getSno, code);       // 通过学号查询
        return baseMapper.selectOne(queryWrapper);
    }
}
