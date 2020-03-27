package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.dao.CourseTargetMapper;
import com.excellent.accreditation.model.base.Options;
import com.excellent.accreditation.model.entity.CourseClass;
import com.excellent.accreditation.model.entity.CourseTarget;
import com.excellent.accreditation.model.entity.GraduationPoint;
import com.excellent.accreditation.model.entity.Questionnaire;
import com.excellent.accreditation.model.form.CourseTargetQuery;
import com.excellent.accreditation.model.vo.CourseTargetVo;
import com.excellent.accreditation.service.ICourseTargetService;
import com.excellent.accreditation.service.IGraduationPointService;
import com.excellent.accreditation.service.IQuestionnaireService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author evildoer
 */
@Service
public class CourseTargetServiceImpl extends ServiceImpl<CourseTargetMapper, CourseTarget> implements ICourseTargetService {
    private final IQuestionnaireService questionnaireService;

    private final IGraduationPointService graduationPointService;

    @Autowired
    public CourseTargetServiceImpl(IQuestionnaireService questionnaireService,IGraduationPointService graduationPointService) {
        this.questionnaireService = questionnaireService;
        this.graduationPointService = graduationPointService;
    }


    @Override
    public void checkCourseTarget(Integer courseTargetId) {
        if (this.getById(courseTargetId) == null) {
            throw new ConflictException("课程目标不存在");
        }
    }

    public PageInfo<CourseTargetVo> pageByQuery(CourseTargetQuery query) {
        LambdaQueryWrapper<CourseTarget> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(query.getTitle())){
            queryWrapper.like(CourseTarget::getTitle, query.getTitle());
        }
        if(query.getQuestionnaireId()!=-1){
            queryWrapper.like(CourseTarget::getQuestionnaireId, query.getQuestionnaireId());
        }
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<CourseTarget> list = this.list(queryWrapper);
        List<CourseTargetVo> lists = convert(list);
        return new PageInfo<>(lists);
    }

    @Override
    public boolean create(CourseTarget courseTarget) {
        this.check(courseTarget, Const.CREATE);
        courseTarget.setCreateTime(LocalDateTime.now());
        courseTarget.setUpdateTime(LocalDateTime.now());
        if (this.save(courseTarget)){
            return true;
        }
        throw new DatabaseException("未知异常, 数据库操作失败");
    }

    @Override
    public List<ExcelResult> saveBachByExcel(MultipartFile file) {
        return null;
    }

    @Override
    public void check(CourseTarget courseTarget, Integer type) {
        if(type.equals(Const.CREATE)||courseTarget.getQuestionnaireId()!=null){
            questionnaireService.checkQuestionnaire(courseTarget.getQuestionnaireId());
        }
        if(type.equals(Const.CREATE)||courseTarget.getPointId()!=null){
            graduationPointService.checkGraduationPoint(courseTarget.getPointId());
        }
    }

    public  List<CourseTargetVo> convert (List<CourseTarget> courseTargets){
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
            Questionnaire questionnaire = questionnaireService.getById(courseTargets.get(i).getQuestionnaireId());
            GraduationPoint graduationPoint = graduationPointService.getById(courseTargets.get(i).getPointId());
            courseTargetVo.setQuestionnaire(questionnaire);
            courseTargetVo.setGraduationPoint(graduationPoint);
            String options=courseTargets.get(i).getOptions();
            JSONArray jsonArray = JSONArray.fromObject(options);
            List<Options> optionsList = (List<Options>)JSONArray.toCollection(jsonArray,Options.class);
            courseTargetVo.setOptionsList(optionsList);
            courseTargetVoList.add(courseTargetVo);
        }
        return  courseTargetVoList;
    }

}
