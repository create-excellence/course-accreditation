package com.excellent.mybatis.service.impl;

import com.excellent.mybatis.entity.Student;
import com.excellent.mybatis.mapper.StudentMapper;
import com.excellent.mybatis.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ashe
 * @since 2019-11-14
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
