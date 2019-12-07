package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.model.entity.CourseClass;
import com.excellent.accreditation.model.entity.CourseTarget;
import com.excellent.accreditation.model.form.CourseClassQuery;
import com.excellent.accreditation.model.form.CourseTargetQuery;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author evildoer
 */
public interface ICourseTargetService extends IService<CourseTarget> {

    void checkCourseTarget(Integer courseTargetId);


    PageInfo<CourseTarget> pageByQuery(CourseTargetQuery query);

    boolean create(CourseTarget courseTarget);

    List<ExcelResult> saveBachByExcel(MultipartFile file);

    void check(CourseTarget courseTarget, Integer type);
}
