package com.excellent.accreditation.service;

import com.excellent.accreditation.model.entity.CourseEvaluation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.model.form.CourseEvaluationQuery;
import com.excellent.accreditation.model.vo.CourseEvaluationVo;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author evildoer
 * @since 2020-03-28
 */
public interface ICourseEvaluationService extends IService<CourseEvaluation> {

    void checkCourseEvaluation(Integer CourseEvaluationId);

    PageInfo<CourseEvaluationVo>  getMyCourseEvaluation(CourseEvaluationQuery  query);

    PageInfo<CourseEvaluationVo> pageByQuery(CourseEvaluationQuery query);

    boolean create(CourseEvaluation courseEvaluation);

    void check(CourseEvaluation CourseEvaluation, Integer type);
}
