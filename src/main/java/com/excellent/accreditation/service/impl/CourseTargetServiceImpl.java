package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.dao.CourseTargetMapper;
import com.excellent.accreditation.manage.UserManage;
import com.excellent.accreditation.model.base.Options;
import com.excellent.accreditation.model.entity.*;
import com.excellent.accreditation.model.form.CourseTargetQuery;
import com.excellent.accreditation.model.vo.CourseTargetVo;
import com.excellent.accreditation.service.ICourseTargetService;
import com.excellent.accreditation.service.IGraduationPointService;
import com.excellent.accreditation.service.IQuestionnaireService;
import com.excellent.accreditation.service.ISelfEvaluationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author evildoer
 */
@Service
@Transactional
public class CourseTargetServiceImpl extends ServiceImpl<CourseTargetMapper, CourseTarget> implements ICourseTargetService {
    private final IQuestionnaireService questionnaireService;

    private final IGraduationPointService graduationPointService;


    private  final  UserManage userManage;


    private  final Integer UP= 0;

    private  final Integer DOWN= 1;

    private  final Integer MIN_SEQUENCE= 1;

    @Autowired
    public CourseTargetServiceImpl(IQuestionnaireService questionnaireService, IGraduationPointService graduationPointService,  UserManage userManage) {
        this.questionnaireService = questionnaireService;
        this.graduationPointService = graduationPointService;
        this.userManage=userManage;
    }


    @Override
    public void checkCourseTarget(Integer courseTargetId) {
        if (this.getById(courseTargetId) == null) {
            throw new ConflictException("课程目标不存在");
        }
    }

    @Override
    public boolean delete(Integer courseTargetId) {
        CourseTarget courseTarget=this.getById(courseTargetId);
        if(courseTarget!=null){
            this.baseMapper.partSequenceReduce(courseTarget.getQuestionnaireId(),courseTarget.getSequence());
            if(this.removeById(courseTargetId)){
                return true;
            }
        }
        return false;
    }

    public PageInfo<CourseTargetVo> pageByQuery(CourseTargetQuery query) {
        LambdaQueryWrapper<CourseTarget> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(query.getTitle())){
            queryWrapper.like(CourseTarget::getTitle, query.getTitle());
        }
        if(query.getQuestionnaireId()!=-1){
            queryWrapper.like(CourseTarget::getQuestionnaireId, query.getQuestionnaireId());
        }
        queryWrapper.orderByAsc(CourseTarget::getSequence);
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<CourseTarget> list = this.list(queryWrapper);
        List<CourseTargetVo> lists = convert(list);
        return new PageInfo<>(lists);
    }

    @Override
    public boolean create(CourseTarget courseTarget) {
        this.check(courseTarget, Const.CREATE);
        courseTarget.setSequence(this.getMax(courseTarget.getQuestionnaireId())+1);
        courseTarget.setCreateTime(LocalDateTime.now());
        courseTarget.setUpdateTime(LocalDateTime.now());

        if (this.save(courseTarget)){
            return true;
        }
        throw new DatabaseException("未知异常, 数据库操作失败");
    }

    @Override
    public List<CourseTarget> getByQuestionnaire(Integer questionnaireId) {
        LambdaQueryWrapper<CourseTarget> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(CourseTarget::getSequence);
        queryWrapper.eq(CourseTarget::getQuestionnaireId,queryWrapper);
        return this.list(queryWrapper);
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

    @Override
    public boolean moveQuestion(Integer courseTargetId, Integer operate) {
        if(UP.equals(operate)||DOWN.equals(operate)){
            CourseTarget courseTarget= this.getById(courseTargetId);
            if(courseTarget!=null){
                if(UP.equals(operate)){
                    //如果序号为最小
                    if(MIN_SEQUENCE.equals(courseTarget.getSequence())){
                        return true;
                    }
                    CourseTarget aboveCourseTarget = this.getBySequence(courseTarget.getQuestionnaireId(),courseTarget.getSequence()-1);
                    courseTarget.setUpdateTime(LocalDateTime.now());
                    courseTarget.setSequence(courseTarget.getSequence()-1);
                    aboveCourseTarget.setUpdateTime(LocalDateTime.now());
                    aboveCourseTarget.setSequence(aboveCourseTarget.getSequence()+1);
                    this.saveOrUpdate(aboveCourseTarget);
                }else{
                    //如果序号为最大
                    if(this.getMax(courseTarget.getQuestionnaireId()).equals(courseTarget.getSequence())){
                        return true;
                    }
                    CourseTarget belowCourseTarget = this.getBySequence(courseTarget.getQuestionnaireId(),courseTarget.getSequence()+1);
                    courseTarget.setUpdateTime(LocalDateTime.now());
                    courseTarget.setSequence(courseTarget.getSequence()+1);
                    belowCourseTarget.setUpdateTime(LocalDateTime.now());
                    belowCourseTarget.setSequence(belowCourseTarget.getSequence()-1);
                    this.saveOrUpdate(belowCourseTarget);
                }
                this.saveOrUpdate(courseTarget);
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean copyQuestion(Integer courseTargetId) {
        CourseTarget courseTarget = this.getById(courseTargetId);
        if(courseTarget!=null){
            courseTarget.setId(null);
            courseTarget.setSequence(courseTarget.getSequence()+1);
            courseTarget.setCreateTime(LocalDateTime.now());
            courseTarget.setUpdateTime(LocalDateTime.now());
            this.baseMapper.partSequenceIncrease(courseTarget.getQuestionnaireId(),courseTarget.getSequence());
            if(this.save(courseTarget)){
                return true;
            }
        }
        return false;
    }

    @Override
    public CourseTarget getBySequence(Integer questionnaireId, Integer sequence) {
        LambdaQueryWrapper<CourseTarget> queryWrapper= new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseTarget::getQuestionnaireId,questionnaireId);
        queryWrapper.eq(CourseTarget::getSequence,sequence);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<GraduationPoint> point(Integer questionnaireId) {
        List<GraduationPoint> list = baseMapper.point(questionnaireId);
        return list;
    }

    public  List<CourseTargetVo> convert (List<CourseTarget> courseTargets){
        List<CourseTargetVo> courseTargetVoList = new ArrayList<>();
        for(int i=0;i<courseTargets.size();i++){
            CourseTargetVo courseTargetVo = new CourseTargetVo();
            BeanUtils.copyProperties(courseTargets.get(i),courseTargetVo);
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




    private Integer getMax(Integer questionnaireId){
        LambdaQueryWrapper<CourseTarget> queryWrapper= new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseTarget::getQuestionnaireId,questionnaireId);
        return  this.count(queryWrapper);
    }

}
