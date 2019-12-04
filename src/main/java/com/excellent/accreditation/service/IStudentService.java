package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.model.entity.Student;
import com.excellent.accreditation.model.form.StudentQuery;

/**
 * @Author evildoer
 */
public interface IStudentService extends IService<Student> {

    Student getByCode(String code);

    void checkCode(String sno);

    IPage<Student> pageByQuery(StudentQuery studentQuery);

    boolean create(Student student);
}
