package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.model.entity.SelfEvaluation;
import com.excellent.accreditation.model.form.SelfEvaluationQuery;
import com.github.pagehelper.PageInfo;

/**
 * @Author evildoer
 */
public interface ISelfEvaluationService extends IService<SelfEvaluation> {

    boolean create(SelfEvaluation selfEvaluation);

    PageInfo<SelfEvaluation> pageByQuery(SelfEvaluationQuery selfEvaluationQuery);
}
