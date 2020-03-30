package com.excellent.accreditation.service.impl;

import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.exception.CommonException;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.TimeException;
import com.excellent.accreditation.dao.CourseClassMapper;
import com.excellent.accreditation.manage.UserManage;
import com.excellent.accreditation.model.entity.CourseEvaluation;
import com.excellent.accreditation.dao.CourseEvaluationMapper;
import com.excellent.accreditation.model.entity.Questionnaire;
import com.excellent.accreditation.model.form.CourseEvaluationQuery;
import com.excellent.accreditation.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;

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

    @Autowired
    public CourseEvaluationServiceImpl(IQuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;

    }


    @Override
    public void checkCourseEvaluation(Integer CourseEvaluationId) {
        if (this.getById(CourseEvaluationId) == null) {
            throw new ConflictException("课程不存在");
        }
    }

    @Override
    public PageInfo<CourseEvaluation> getMyCourseEvaluation() {
        return null;
    }

    @Override
    public PageInfo<CourseEvaluation> pageByQuery(CourseEvaluationQuery query) {
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
