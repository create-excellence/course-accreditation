package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.model.entity.Semester;
import com.excellent.accreditation.model.form.SemesterQuery;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author evildoer
 */
public interface ISemesterService extends IService<Semester> {
    void checkName(String name,Integer semesterId);

    boolean create(Semester semester);

    PageInfo<Semester> pageByQuery(SemesterQuery semesterQuery);

    void  checkSemester(Integer semester);

    Semester getByName(String name);

    List<ExcelResult> saveBachByExcel(MultipartFile file);
}
