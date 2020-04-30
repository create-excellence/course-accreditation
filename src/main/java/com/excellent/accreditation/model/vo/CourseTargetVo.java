package com.excellent.accreditation.model.vo;

import com.excellent.accreditation.model.base.Options;
import com.excellent.accreditation.model.entity.CourseTarget;
import com.excellent.accreditation.model.entity.GraduationPoint;
import com.excellent.accreditation.model.entity.Questionnaire;
import com.excellent.accreditation.model.entity.SelfEvaluation;
import com.excellent.accreditation.service.IQuestionnaireService;
import lombok.Data;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


@Data
public class CourseTargetVo extends CourseTarget {

    private Questionnaire questionnaire;
    private List<Options> optionsList;
    private GraduationPoint graduationPoint;
    private  String choose;
    private Integer selfEvaluationId;


}
