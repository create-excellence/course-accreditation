package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.model.entity.Course;
import com.excellent.accreditation.model.entity.SupportingCourse;
import com.excellent.accreditation.model.form.CourseQuery;
import com.excellent.accreditation.model.form.SupportingCourseQuery;
import com.excellent.accreditation.model.vo.SupportingCourseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author evildoer
 */
public interface ISupportingCourseService extends IService<SupportingCourse> {

    List<SupportingCourseVo> pageByQuery(SupportingCourseQuery query);

    boolean create(SupportingCourse supportingCourse);

    List<ExcelResult> saveBachByExcel(MultipartFile file);

    void check(SupportingCourse supportingCourse,Integer checkType);

    void checkSupportingCourse(Integer supportingCourseId);

    SupportingCourseVo getVoById(Integer id);

}
