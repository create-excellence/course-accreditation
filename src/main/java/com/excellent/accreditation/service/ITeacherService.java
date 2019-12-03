package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.model.entity.Teacher;
import com.excellent.accreditation.model.form.TeacherQuery;

/**
 * @Author evildoer
 */
public interface ITeacherService extends IService<Teacher> {

    Teacher getByCode(String code);

    void checkJno(String jno);

    IPage<Teacher> pageByQuery(TeacherQuery teacherQuery);
}
