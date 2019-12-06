package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.model.entity.Teacher;
import com.excellent.accreditation.model.form.TeacherQuery;
import com.github.pagehelper.PageInfo;

/**
 * @Author evildoer
 */
public interface ITeacherService extends IService<Teacher> {

    Teacher getByCode(String code);

    void checkJno(String jno);

    PageInfo<Teacher> pageByQuery(TeacherQuery teacherQuery);

    boolean create(Teacher teacher);

    void checkTeacher(Integer teacherId);
}
