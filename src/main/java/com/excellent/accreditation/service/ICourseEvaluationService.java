package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.model.entity.CourseEvaluation;
import com.excellent.accreditation.model.entity.CourseTarget;
import com.excellent.accreditation.model.form.CourseEvaluationQuery;
import com.excellent.accreditation.model.form.CourseEvaluationStudentQuery;
import com.excellent.accreditation.model.vo.CourseEvaluationStudentVo;
import com.excellent.accreditation.model.vo.CourseEvaluationVo;
import com.excellent.accreditation.model.vo.CourseTargetVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author evildoer
 * @since 2020-03-28
 */
public interface ICourseEvaluationService extends IService<CourseEvaluation> {

    void checkCourseEvaluation(Integer CourseEvaluationId);

    PageInfo<CourseEvaluationVo>  getMyCourseEvaluation(CourseEvaluationQuery  query);

    PageInfo<CourseEvaluationStudentVo>  getCourseEvaluationStudent(CourseEvaluationStudentQuery courseEvaluationStudentQuery);

    PageInfo<CourseEvaluationVo> pageByQuery(CourseEvaluationQuery query);

    boolean create(CourseEvaluation courseEvaluation);

    boolean startEvaluation(Integer courseEvaluationId);

   List<CourseTargetVo> getQuestions(Integer courseEvaluationId );

    void check(CourseEvaluation CourseEvaluation, Integer type);
}
