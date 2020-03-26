package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.model.entity.Questionnaire;
import com.excellent.accreditation.model.form.QuestionnaireQuery;
import com.excellent.accreditation.model.vo.QuestionnaireVo;
import com.github.pagehelper.PageInfo;

/**
 * @Author evildoer
 */
public interface IQuestionnaireService extends IService<Questionnaire> {

    boolean create(Questionnaire questionnaire);

    PageInfo<QuestionnaireVo> pageByQuery(QuestionnaireQuery questionnaireQuery);

    void  checkQuestionnaire(Integer questionnaireId);
}
