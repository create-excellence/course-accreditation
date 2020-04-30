package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.dao.SelfEvaluationMapper;
import com.excellent.accreditation.model.entity.CourseTarget;
import com.excellent.accreditation.model.entity.SelfEvaluation;
import com.excellent.accreditation.model.form.CourseEvaluationStudentQuery;
import com.excellent.accreditation.model.form.SelfEvaluationQuery;
import com.excellent.accreditation.model.vo.CourseEvaluationStudentVo;
import com.excellent.accreditation.service.ICourseTargetService;
import com.excellent.accreditation.service.ISelfEvaluationService;
import com.excellent.accreditation.service.IStudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author evildoer
 */
@Service
@Transactional
public class SelfEvaluationServiceImpl extends ServiceImpl<SelfEvaluationMapper, SelfEvaluation> implements ISelfEvaluationService {

    private final IStudentService studentService;

    private final ICourseTargetService courseTargetService;

    public SelfEvaluationServiceImpl(IStudentService studentService, ICourseTargetService courseTargetService) {
        this.studentService = studentService;
        this.courseTargetService = courseTargetService;
    }

    @Override
    public boolean create(SelfEvaluation selfEvaluation) {
        selfEvaluation.setCreateTime(LocalDateTime.now());
        selfEvaluation.setUpdateTime(LocalDateTime.now());
        studentService.checkStudent(selfEvaluation.getStudentId());                     // 查看是否有对应的学生
        courseTargetService.checkCourseTarget(selfEvaluation.getCourseTargetId());      // 查看是否有对应的课程目标
        boolean result = this.save(selfEvaluation);
        // 操作成功
        if (result)
            return true;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }

    @Override
    public PageInfo<SelfEvaluation> pageByQuery(SelfEvaluationQuery query) {
        LambdaQueryWrapper<SelfEvaluation> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(query.getAnswer()))
            queryWrapper.like(SelfEvaluation::getAnswer, query.getAnswer());
        if (query.getScore() != null)
            queryWrapper.ge(SelfEvaluation::getScore, query.getScore());
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<SelfEvaluation> list = this.list(queryWrapper);
        return new PageInfo<>(list);
    }

    @Override
    public List<SelfEvaluation> getByStudentId(@NonNull int studentId) {
        LambdaQueryWrapper<SelfEvaluation> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SelfEvaluation::getStudentId, studentId);
        return this.list(queryWrapper);
    }

    @Override
    public List<SelfEvaluation> getByStudentIdAndEvaluation(Integer studentId, Integer evaluationId) {
        LambdaQueryWrapper<SelfEvaluation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SelfEvaluation::getStudentId, studentId);
        queryWrapper.eq(SelfEvaluation::getCourseEvaluationId, evaluationId);
        queryWrapper.orderByAsc(SelfEvaluation::getId);
        return this.list(queryWrapper);
    }

    @Override
          public int countSelfEvaluationById(int selfEvaluationId) {
        LambdaQueryWrapper<SelfEvaluation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SelfEvaluation::getId, selfEvaluationId);
        return this.baseMapper.selectCount(queryWrapper);
    }

    @Override
        public SelfEvaluation selectOneSelfEvaluation(Integer courseEvaluationId,Integer studentId) {
        LambdaQueryWrapper<SelfEvaluation> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(SelfEvaluation::getCourseEvaluationId,courseEvaluationId);
        queryWrapper.eq(SelfEvaluation::getStudentId,studentId);
        List<SelfEvaluation> list =  this.baseMapper.selectList(queryWrapper);
        return  list.size()>0?list.get(0):null;

    }

    @Override
    public boolean createEvaluations(Integer studentId, Integer courseEvaluationId, Integer questionnaireId) {
        List<CourseTarget> courseTargets =courseTargetService.getByQuestionnaire(questionnaireId);
        List<SelfEvaluation> selfEvaluationList =new ArrayList<>();
        for (int i = 0; i <courseTargets.size() ; i++) {
            SelfEvaluation selfEvaluation =new SelfEvaluation();
            selfEvaluation.setCreateTime(LocalDateTime.now());
            selfEvaluation.setStudentId(studentId);
            selfEvaluation.setCourseEvaluationId(courseEvaluationId);
            selfEvaluation.setCourseTargetId(courseTargets.get(i).getId());
            selfEvaluation.setScore(0);
            selfEvaluationList.add(selfEvaluation);
        }
        return  this.saveOrUpdateBatch(selfEvaluationList);
    }


}
