package com.excellent.accreditation.model.vo;

import com.excellent.accreditation.model.base.Options;
import com.excellent.accreditation.model.entity.CourseTarget;
import lombok.Data;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseTargetVo extends CourseTarget {

    private List<Options> optionsList;

    public static List<CourseTargetVo> convert (List<CourseTarget> courseTargets){
        List<CourseTargetVo> courseTargetVoList = new ArrayList<>();
        for(int i=0;i<courseTargets.size();i++){
            CourseTargetVo courseTargetVo = new CourseTargetVo();
            courseTargetVo.setId(courseTargets.get(i).getId());
            courseTargetVo.setDescribes(courseTargets.get(i).getDescribes());
            courseTargetVo.setQuestionnaireId(courseTargets.get(i).getQuestionnaireId());
            courseTargetVo.setTitle(courseTargets.get(i).getTitle());
            courseTargetVo.setOptionsScore(courseTargets.get(i).getOptionsScore());
            courseTargetVo.setPointId(courseTargets.get(i).getPointId());
            courseTargetVo.setOptions(courseTargets.get(i).getOptions());
            courseTargetVo.setTotalScore(courseTargets.get(i).getTotalScore());
            courseTargetVo.setSequence(courseTargets.get(i).getSequence());
            courseTargetVo.setCreateTime(courseTargets.get(i).getCreateTime());
            courseTargetVo.setUpdateTime(courseTargets.get(i).getUpdateTime());
            String options=courseTargets.get(i).getOptions();
            JSONArray jsonArray = JSONArray.fromObject(options);
            List<Options> optionsList = (List<Options>)JSONArray.toCollection(jsonArray,Options.class);
            courseTargetVo.setOptionsList(optionsList);
            courseTargetVoList.add(courseTargetVo);
        }
        return  courseTargetVoList;
    }
}
