package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.model.entity.SelfEvaluation;
import com.excellent.accreditation.model.form.CourseEvaluationStudentQuery;
import com.excellent.accreditation.model.form.SelfEvaluationQuery;
import com.excellent.accreditation.model.vo.CourseEvaluationStudentVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author evildoer
 */
public interface ISelfEvaluationService extends IService<SelfEvaluation> {

    boolean create(SelfEvaluation selfEvaluation);

    PageInfo<SelfEvaluation> pageByQuery(SelfEvaluationQuery selfEvaluationQuery);

    List<SelfEvaluation> getByStudentId(int studentId);

    List<SelfEvaluation> getByStudentIdAndEvaluation(Integer studentId,Integer evaluationId);

    int countSelfEvaluationById(int selfEvaluationId);

    SelfEvaluation selectOneSelfEvaluation(Integer courseEvaluationId,Integer studentId);

    boolean createEvaluations(Integer studentId,Integer courseEvaluationId,Integer questionnaireId);
}
