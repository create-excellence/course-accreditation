package com.excellent.accreditation.service.impl;

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

}
