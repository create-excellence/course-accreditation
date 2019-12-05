package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.model.entity.Student;
import com.excellent.accreditation.model.form.StudentQuery;
import com.excellent.accreditation.model.vo.StudentVo;
import com.github.pagehelper.PageInfo;

/**
 * @Author evildoer
 */
public interface IStudentService extends IService<Student> {

    void checkStudent(Integer studentId);

    Student getByCode(String code);

    void checkCode(String sno);

    PageInfo<StudentVo> pageByQuery(StudentQuery studentQuery);

    boolean create(Student student);
}
