package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.TimeException;
import com.excellent.accreditation.dao.CourseEvaluationMapper;
import com.excellent.accreditation.manage.UserManage;
import com.excellent.accreditation.model.entity.*;
import com.excellent.accreditation.model.form.CourseClassQuery;
import com.excellent.accreditation.model.form.CourseEvaluationQuery;
import com.excellent.accreditation.model.vo.CourseEvaluationVo;
import com.excellent.accreditation.service.*;
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
 * 服务实现类
 * </p>
 *
 * @author evildoer
 * @since 2020-03-28
 */
@Service
public class CourseEvaluationServiceImpl extends ServiceImpl<CourseEvaluationMapper, CourseEvaluation> implements ICourseEvaluationService {

    // 正在进行的课程评价状态码
    private final int PROCESSINGCODE = 1;

    private final IQuestionnaireService questionnaireService;

    private final ICourseClassService courseClassService;

    private final ICourseService courseService;

    private final ISelfEvaluationService selfEvaluationService;

    private final UserManage userManage;

    @Autowired
    public CourseEvaluationServiceImpl(IQuestionnaireService questionnaireService, UserManage userManage, ICourseClassService courseClassService, ICourseService courseService, ISelfEvaluationService selfEvaluationService) {
        this.questionnaireService = questionnaireService;
        this.userManage = userManage;
        this.courseClassService = courseClassService;
        this.courseService = courseService;
        this.selfEvaluationService = selfEvaluationService;
    }


    @Override
    public void checkCourseEvaluation(Integer CourseEvaluationId) {
        if (this.getById(CourseEvaluationId) == null) {
            throw new ConflictException("课程不存在");
        }
    }


//    @Override
//    public PageInfo<CourseEvaluationVo> getMyCourseEvaluation(CourseEvaluationQuery query) {
//        List<String> roles = userManage.getRolesByCode(userManage.getCodeByToken());
//        for (String role : roles) {
//            if (role.equals(Const.TEACHER)) {
//                List<Integer> courseClassIds = courseClassService.getMyCourse(new CourseClassQuery()).getList().stream().map(CourseClass::getId).collect(Collectors.toList());
//                LambdaQueryWrapper<CourseEvaluation> queryWrapper = new LambdaQueryWrapper<>();
//                switch (query.getTimeOrder()) {
//                    case 0:
//                        queryWrapper.ge(CourseEvaluation::getUpdateTime, LocalDateTime.now().plusWeeks(-1));
//                    case 1:
//                        queryWrapper.ge(CourseEvaluation::getUpdateTime, LocalDateTime.now().plusMonths(-1));
//                    case 2:
//                        queryWrapper.ge(CourseEvaluation::getUpdateTime, LocalDateTime.now().plusYears(-1));
//                }
//                if (query.getStatus() != null && query.getStatus() != -1) {
//                    queryWrapper.eq(CourseEvaluation::getStatus, query.getStatus());
//                }
//                if (StringUtils.isNotEmpty(query.getName())) {
//                    queryWrapper.like(CourseEvaluation::getName, query.getName());
//                }
//                queryWrapper.in(CourseEvaluation::getCourseClassId, courseClassIds);
//                PageHelper.startPage(query.getPage(), query.getPageSize());
//                List<CourseEvaluation> list = this.list(queryWrapper);
//                List<CourseEvaluationVo> data = new ArrayList<>();
//                for (CourseEvaluation item : list) {
//                    CourseClass courseClass = courseClassService.getById(item.getCourseClassId());
//                    Course course = courseService.getById(courseClass.getCourseId());
//                    Questionnaire questionnaire = questionnaireService.getById(item.getQuestionnaireId());
//                    CourseEvaluationVo courseEvaluationVo = new CourseEvaluationVo(item);
//                    courseEvaluationVo.setCourse(courseClass.getNo() + course.getName());
//                    courseEvaluationVo.setQuestionnaire(questionnaire.getName());
//                    data.add(courseEvaluationVo);
//                }
//                return new PageInfo<>(data);
//            }
//            if (role.equals(Const.STUDENT)) {
//                //TODO
//            }
//        }
//        return null;
//    }

    @Override
    public PageInfo<CourseEvaluationVo> getMyCourseEvaluation(CourseEvaluationQuery query) {
        List<String> roles = userManage.getRolesByCode(userManage.getCodeByToken());
        List<Integer> courseClassIds = courseClassService.getMyCourse(new CourseClassQuery()).getList().stream().map(CourseClass::getId).collect(Collectors.toList());
        LambdaQueryWrapper<CourseEvaluation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(CourseEvaluation::getCourseClassId, courseClassIds);
        if (StringUtils.isNotEmpty(query.getName())) {
            queryWrapper.like(CourseEvaluation::getName, query.getName());
        }
        for (String role : roles) {
            if (role.equals(Const.TEACHER)) {
                if (query.getStatus() != null && query.getStatus() != -1 && query.getStatus() != -2) {
                    queryWrapper.eq(CourseEvaluation::getStatus, query.getStatus());
                }
                List<CourseEvaluationVo> data = this.filterAndToVo(queryWrapper, query);
                return new PageInfo<>(data);
            }
            if (role.equals(Const.STUDENT)) {
                List<Integer> selfEvaluationIds = selfEvaluationService.getByStudentId(userManage.getUserInfo().getId()).stream().map(SelfEvaluation::getCourseEvaluationId).collect(Collectors.toList());
                // 解决selfEvaluationIds为空报错的bug
                if (selfEvaluationIds == null || selfEvaluationIds.size() == 0) {
                    return null;
                }

                // 学生端只显示选课班级发布的课程评价
                // queryWrapper.eq(CourseEvaluation::getStatus, PROCESSINGCODE);
                if (query.getStatus() != null && query.getStatus() != -1 && query.getStatus() != -2) {
                    queryWrapper.eq(CourseEvaluation::getStatus, query.getStatus());
                } else if (query.getStatus() == -2) {  // 显示学生已经评价的
                    queryWrapper.in(CourseEvaluation::getId, selfEvaluationIds);
                }
                queryWrapper.in(CourseEvaluation::getCourseClassId, courseClassIds);
                List<CourseEvaluationVo> data = this.filterAndToVo(queryWrapper, query);
                for (int i = 0; i < data.size(); i++) {
                    CourseEvaluationVo courseEvaluationVo = data.get(i);
                    // 如果已经评价
                    if (selfEvaluationIds.contains(courseEvaluationVo.getId())) {
                        courseEvaluationVo.setIsEvaluation(Boolean.TRUE);
                        data.set(i, courseEvaluationVo);
                    } else {
                        courseEvaluationVo.setIsEvaluation(Boolean.FALSE);
                        data.set(i, courseEvaluationVo);
                    }
                }
                return new PageInfo<>(data);
            }
        }

        return null;
    }

    @Override
    public PageInfo<CourseEvaluationVo> pageByQuery(CourseEvaluationQuery query) {
        LambdaQueryWrapper<CourseEvaluation> queryWrapper = new LambdaQueryWrapper<>();
        if (query.getStatus() != null && query.getStatus() != -1 && query.getStatus() != -2) {
            queryWrapper.eq(CourseEvaluation::getStatus, query.getStatus());
        }
        if (StringUtils.isNotEmpty(query.getName())) {
            queryWrapper.like(CourseEvaluation::getName, query.getName());
        }
        List<CourseEvaluationVo> data = this.filterAndToVo(queryWrapper, query);
        return new PageInfo<>(data);
    }

    /**
     * @Author 安羽兮
     * @Description 按时间排序状态码筛选, 并且转为CourseEvaluationVo类
     * @Date 18:06 2020/4/8
     * @Param [queryWrapper, query]
     * @Return java.util.List<com.excellent.accreditation.model.vo.CourseEvaluationVo>
     **/
    private List<CourseEvaluationVo> filterAndToVo(LambdaQueryWrapper<CourseEvaluation> queryWrapper, CourseEvaluationQuery query) {
        if (query.getTimeOrder() != null) {
            switch (query.getTimeOrder()) {
                case 0:
                    queryWrapper.ge(CourseEvaluation::getUpdateTime, LocalDateTime.now().plusWeeks(-1));
                case 1:
                    queryWrapper.ge(CourseEvaluation::getUpdateTime, LocalDateTime.now().plusMonths(-1));
                case 2:
                    queryWrapper.ge(CourseEvaluation::getUpdateTime, LocalDateTime.now().plusYears(-1));
            }
        }
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<CourseEvaluation> list = this.list(queryWrapper);
        List<CourseEvaluationVo> data = new ArrayList<>();
        for (CourseEvaluation item : list) {
            CourseClass courseClass = courseClassService.getById(item.getCourseClassId());
            Course course = courseService.getById(courseClass.getCourseId());
            Questionnaire questionnaire = questionnaireService.getById(item.getQuestionnaireId());
            CourseEvaluationVo courseEvaluationVo = new CourseEvaluationVo(item);
            courseEvaluationVo.setCourse(courseClass.getNo() + course.getName());
            courseEvaluationVo.setQuestionnaire(questionnaire.getName());
            courseEvaluationVo.setCount(this.selfEvaluationService.countSelfEvaluationById(item.getId()));
            data.add(courseEvaluationVo);
        }
        return data;
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
        if (startTime.compareTo(LocalDateTime.now()) < 0) {
            throw new TimeException("开始时间不能早于当前时间");
        }
        if (startTime.compareTo(endTime) > 0) {
            throw new TimeException("开始时间必须早于结束时间");
        }
        if (Duration.between(startTime, endTime).toMinutes() < 10) {
            throw new TimeException("开始时间必须与结束时间有十分钟以上的间隔");
        }
        questionnaireService.checkQuestionnaire(courseEvaluation.getQuestionnaireId());
    }
}
