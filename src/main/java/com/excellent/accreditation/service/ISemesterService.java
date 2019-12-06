package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.model.entity.Semester;
import com.excellent.accreditation.model.form.SemesterQuery;
import com.github.pagehelper.PageInfo;

/**
 * @Author evildoer
 */
public interface ISemesterService extends IService<Semester> {
    void checkName(String name);

    boolean create(Semester semester);

    PageInfo<Semester> pageByQuery(SemesterQuery semesterQuery);

    void  checkSemester(Integer semester);
}
