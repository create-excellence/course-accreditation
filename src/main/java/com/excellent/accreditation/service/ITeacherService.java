package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.model.entity.Teacher;
import com.excellent.accreditation.model.form.TeacherQuery;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author evildoer
 */
public interface ITeacherService extends IService<Teacher> {

    Teacher getByCode(String code);

    void checkJno(String jno);

    PageInfo<Teacher> pageByQuery(TeacherQuery teacherQuery);

    List<ExcelResult> saveBachByExcel(MultipartFile file);

    boolean create(Teacher teacher);

    void checkTeacher(Integer teacherId);

    boolean updatePassword(String code, String oldPassword, String newPassword);
}
