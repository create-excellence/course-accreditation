package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.model.entity.CourseClass;
import com.excellent.accreditation.model.entity.CourseTarget;
import com.excellent.accreditation.model.entity.GraduationPoint;
import com.excellent.accreditation.model.form.CourseClassQuery;
import com.excellent.accreditation.model.form.CourseTargetQuery;
import com.excellent.accreditation.model.vo.CourseTargetVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * @Author evildoer
 */
public interface ICourseTargetService extends IService<CourseTarget> {

    void checkCourseTarget(Integer courseTargetId);

    boolean delete(Integer courseTargetId);

    PageInfo<CourseTargetVo> pageByQuery(CourseTargetQuery query);

    boolean create(CourseTarget courseTarget);

    List<ExcelResult> saveBachByExcel(MultipartFile file);

    void check(CourseTarget courseTarget, Integer type);

    boolean moveQuestion(Integer courseTargetId, Integer operate);

    boolean copyQuestion(Integer courseTargetId);

    CourseTarget getBySequence(Integer questionnaireId, Integer sequence);

    List<GraduationPoint> point(Integer questionnaireId);
}
