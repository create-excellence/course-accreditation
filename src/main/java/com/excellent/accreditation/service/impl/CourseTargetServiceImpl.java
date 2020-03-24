package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.dao.CourseTargetMapper;
import com.excellent.accreditation.model.entity.CourseClass;
import com.excellent.accreditation.model.entity.CourseTarget;
import com.excellent.accreditation.model.form.CourseTargetQuery;
import com.excellent.accreditation.model.vo.CourseTargetVo;
import com.excellent.accreditation.service.ICourseTargetService;
import com.excellent.accreditation.service.IGraduationPointService;
import com.excellent.accreditation.service.IQuestionnaireService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<CourseTarget> list = this.list(queryWrapper);
        List<CourseTargetVo> lists = CourseTargetVo.convert(list);
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

}
