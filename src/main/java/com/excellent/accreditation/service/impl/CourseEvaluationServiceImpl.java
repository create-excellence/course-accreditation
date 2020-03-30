package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.TimeException;
import com.excellent.accreditation.manage.UserManage;
import com.excellent.accreditation.model.entity.Course;
import com.excellent.accreditation.model.entity.CourseClass;
import com.excellent.accreditation.model.entity.CourseEvaluation;
import com.excellent.accreditation.dao.CourseEvaluationMapper;
import com.excellent.accreditation.model.entity.Questionnaire;
import com.excellent.accreditation.model.form.CourseClassQuery;
import com.excellent.accreditation.model.form.CourseEvaluationQuery;
import com.excellent.accreditation.model.vo.CourseEvaluationVo;
import com.excellent.accreditation.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evildoer
 * @since 2020-03-28
 */
@Service
public class CourseEvaluationServiceImpl extends ServiceImpl<CourseEvaluationMapper, CourseEvaluation> implements ICourseEvaluationService {


    private final IQuestionnaireService questionnaireService;

    private final ICourseClassService courseClassService;

    private final ICourseService courseService;

    private final UserManage userManage;

    @Autowired
    public CourseEvaluationServiceImpl(IQuestionnaireService questionnaireService,UserManage userManage,ICourseClassService courseClassService,ICourseService courseService) {
        this.questionnaireService = questionnaireService;
        this.userManage=userManage;
        this.courseClassService=courseClassService;
        this.courseService=courseService;
    }


    @Override
    public void checkCourseEvaluation(Integer CourseEvaluationId) {
        if (this.getById(CourseEvaluationId) == null) {
            throw new ConflictException("课程不存在");
        }
    }

    @Override
    public PageInfo<CourseEvaluationVo> getMyCourseEvaluation(CourseEvaluationQuery  query) {
        List<String> roles = userManage.getRolesByCode(userManage.getCodeByToken());
        for (String role :roles) {
            if(role.equals(Const.TEACHER)){
                List<Integer> courseClassIds = courseClassService.getMyCourse(new CourseClassQuery()).getList().stream().map(CourseClass:: getId).collect(Collectors.toList());
                LambdaQueryWrapper<CourseEvaluation> queryWrapper = new LambdaQueryWrapper<>();
                switch (query.getTimeOrder()){
                    case 0:
                        queryWrapper.ge(CourseEvaluation::getUpdateTime,LocalDateTime.now().plusWeeks(-1));
                    case 1:
                        queryWrapper.ge(CourseEvaluation::getUpdateTime,LocalDateTime.now().plusMonths(-1));
                    case 2:
                        queryWrapper.ge(CourseEvaluation::getUpdateTime,LocalDateTime.now().plusYears(-1));
                }
                if(query.getStatus()!=null&&query.getStatus()!=-1) {
                    queryWrapper.eq(CourseEvaluation::getStatus,query.getStatus());
                }
                if(StringUtils.isNotEmpty(query.getName())) {
                    queryWrapper.like(CourseEvaluation::getName,query.getName());
                }
                queryWrapper.in(CourseEvaluation::getCourseClassId,courseClassIds);
                PageHelper.startPage(query.getPage(), query.getPageSize());
                List<CourseEvaluation> list =  this.list(queryWrapper);
                List<CourseEvaluationVo> data = new ArrayList<>();
                for (CourseEvaluation item:list) {
                    CourseClass courseClass =courseClassService.getById(item.getCourseClassId());
                    Course course =courseService.getById(courseClass.getCourseId());
                    Questionnaire questionnaire= questionnaireService.getById(item.getQuestionnaireId());
                    CourseEvaluationVo courseEvaluationVo = new CourseEvaluationVo(item);
                    courseEvaluationVo.setCourse(courseClass.getNo()+course.getName());
                    courseEvaluationVo.setQuestionnaire(questionnaire.getName());
                    data.add(courseEvaluationVo);
                }
                return new PageInfo<>(data);
            }
            if(role.equals(Const.STUDENT)){
                //TODO
            }
        }


        return null;
    }

    @Override
    public PageInfo<CourseEvaluationVo> pageByQuery(CourseEvaluationQuery query) {
        return null;
    }

    @Override
    public boolean create(CourseEvaluation courseEvaluation) {
        this.check(courseEvaluation, Const.CREATE);
        courseEvaluation.setStatus(Const.NOT_STARTED);
        courseEvaluation.setUpdateTime(LocalDateTime.now());
        courseEvaluation.setCreateTime(LocalDateTime.now());
        if (this.save(courseEvaluation)) {
            return true;
        }
        throw new DatabaseException("未知异常, 数据库操作失败");
    }

    @Override
    public void check(CourseEvaluation courseEvaluation, Integer type) {
        final LocalDateTime startTime = courseEvaluation.getStartTime();
        final LocalDateTime endTime = courseEvaluation.getEndTime();
        if(startTime.compareTo(LocalDateTime.now())<0){
            throw  new TimeException("开始时间不能早于当前时间");
        }
        if(startTime.compareTo(endTime)>0){
            throw  new TimeException("开始时间必须早于结束时间");
        }
        if( Duration.between(startTime,endTime).toMinutes() <10){
            throw  new TimeException("开始时间必须与结束时间有十分钟以上的间隔");
        }
       questionnaireService.checkQuestionnaire(courseEvaluation.getQuestionnaireId());
    }
}
