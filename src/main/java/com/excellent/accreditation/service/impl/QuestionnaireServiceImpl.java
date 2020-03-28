package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.dao.QuestionnaireMapper;
import com.excellent.accreditation.manage.UserManage;
import com.excellent.accreditation.model.entity.Questionnaire;
import com.excellent.accreditation.model.form.QuestionnaireQuery;
import com.excellent.accreditation.model.vo.QuestionnaireVo;
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

    private final QuestionnaireMapper questionnaireMapper;

    private final UserManage userManage;

    public QuestionnaireServiceImpl(QuestionnaireMapper questionnaireMapper,UserManage userManage) {
        this.questionnaireMapper = questionnaireMapper;
        this.userManage = userManage;
    }

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
    public PageInfo<QuestionnaireVo> pageByQuery(QuestionnaireQuery query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<QuestionnaireVo> list =questionnaireMapper.pageByQuery(query.getTotalScore(),query.getName(),query.getCourseClassId(),null);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<QuestionnaireVo> getMyQuestionnaire(QuestionnaireQuery query) {
        List<String> roles = userManage.getRolesByCode(userManage.getCodeByToken());
        for (String role :roles) {
            if(role.equals(Const.TEACHER)){
                PageHelper.startPage(query.getPage(), query.getPageSize());
                Integer teacherId = userManage.getUserInfo().getId();
                List<QuestionnaireVo> list =questionnaireMapper.pageByQuery(query.getTotalScore(),query.getName(),query.getCourseClassId(),teacherId);
                return new PageInfo<>(list);
            }
            if(role.equals(Const.STUDENT)){
                //TODO
            }
        }


        return null;
    }

    @Override
    public void checkQuestionnaire(Integer questionnaireId) {
        if (this.getById(questionnaireId) == null) {
            throw new ConflictException("问卷不存在");
        }
    }
}
