package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.dao.SelfEvaluationMapper;
import com.excellent.accreditation.model.entity.SelfEvaluation;
import com.excellent.accreditation.model.form.SelfEvaluationQuery;
import com.excellent.accreditation.service.ICourseTargetService;
import com.excellent.accreditation.service.ISelfEvaluationService;
import com.excellent.accreditation.service.IStudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author evildoer
 */
@Service
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
        PageInfo<SelfEvaluation> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<SelfEvaluation> getByStudentId(@NonNull int studentId) {
        LambdaQueryWrapper<SelfEvaluation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(SelfEvaluation::getStudentId, studentId);
        List<SelfEvaluation> list = this.list(queryWrapper);
        return list;
    }

    @Override
    public int countSelfEvaluationById(int selfEvaluationId) {
        LambdaQueryWrapper<SelfEvaluation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SelfEvaluation::getId, selfEvaluationId);
        return this.baseMapper.selectCount(queryWrapper);
    }
}
