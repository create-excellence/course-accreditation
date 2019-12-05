package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.dao.QuestionnaireMapper;
import com.excellent.accreditation.model.entity.Questionnaire;
import com.excellent.accreditation.model.form.QuestionnaireQuery;
import com.excellent.accreditation.service.IQuestionnaireService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author evildoer
 */
@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire> implements IQuestionnaireService {

    @Override
    public boolean create(Questionnaire questionnaire) {
        questionnaire.setCreateTime(LocalDateTime.now());
        questionnaire.setUpdateTime(LocalDateTime.now());
        boolean result = this.save(questionnaire);
        // 操作成功
        if (result)
            return true;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }

    @Override
    public PageInfo<Questionnaire> pageByQuery(QuestionnaireQuery query) {
        LambdaQueryWrapper<Questionnaire> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(query.getName()))
            queryWrapper.like(Questionnaire::getName, query.getName());
        if (query.getTotalScore() != null)
            queryWrapper.ge(Questionnaire::getTotalScore, query.getTotalScore());
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<Questionnaire> list = this.list(queryWrapper);
        PageInfo<Questionnaire> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
