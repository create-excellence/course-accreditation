package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.EmptyException;
import com.excellent.accreditation.common.exception.TimeException;
import com.excellent.accreditation.dao.CourseEvaluationMapper;
import com.excellent.accreditation.manage.UserManage;
import com.excellent.accreditation.model.base.Options;
import com.excellent.accreditation.model.entity.*;
import com.excellent.accreditation.model.form.CourseClassQuery;
import com.excellent.accreditation.model.form.CourseEvaluationQuery;
import com.excellent.accreditation.model.form.CourseEvaluationStudentQuery;
import com.excellent.accreditation.model.vo.CourseEvaluationStudentVo;
import com.excellent.accreditation.model.vo.CourseEvaluationVo;
import com.excellent.accreditation.model.vo.CourseTargetVo;
import com.excellent.accreditation.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import org.springframework.beans.BeanUtils;
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

    private final ISelectCourseService selectCourseService;

    private final ICourseTargetService courseTargetService;

    private final UserManage userManage;

    @Autowired
    public CourseEvaluationServiceImpl(IQuestionnaireService questionnaireService, UserManage userManage, ICourseClassService courseClassService, ICourseService courseService, ISelfEvaluationService selfEvaluationService,ISelectCourseService selectCourseService,ICourseTargetService courseTargetService) {
        this.questionnaireService = questionnaireService;
        this.userManage = userManage;
        this.courseClassService = courseClassService;
        this.courseService = courseService;
        this.selfEvaluationService = selfEvaluationService;
        this.selectCourseService = selectCourseService;
        this.courseTargetService=courseTargetService;
    }


    @Override
    public void checkCourseEvaluation(Integer CourseEvaluationId) {
        if (this.getById(CourseEvaluationId) == null) {
            throw new ConflictException("课程不存在");
        }
    }



    @Override
    public PageInfo<CourseEvaluationVo> getMyCourseEvaluation(CourseEvaluationQuery query) {
        List<String> roles = userManage.getRolesByCode(userManage.getCodeByToken());
        List<Integer> courseClassIds = courseClassService.getMyCourse(new CourseClassQuery()).getList().stream().map(CourseClass::getId).collect(Collectors.toList());
        if(courseClassIds.size()==0){
            return new PageInfo<>(new ArrayList<>());
        }
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
//                if (selfEvaluationIds == null || selfEvaluationIds.size() == 0) {
//                    return null;
//                }

                // 学生端只显示选课班级发布的课程评价
                // queryWrapper.eq(CourseEvaluation::getStatus, PROCESSINGCODE);
                if (query.getStatus() != null && query.getStatus() != -1 && query.getStatus() != -2) {
                    queryWrapper.eq(CourseEvaluation::getStatus, query.getStatus());
                } else if (query.getStatus() == -2) {  // 显示学生已经评价的
                if ( selfEvaluationIds.size()> 0) {
                    queryWrapper.in(CourseEvaluation::getId, selfEvaluationIds);
                }else {
                   return new PageInfo<>(new ArrayList<>());
                }

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
    public PageInfo<CourseEvaluationStudentVo> getCourseEvaluationStudent(CourseEvaluationStudentQuery courseEvaluationStudentQuery) {
        CourseEvaluation courseEvaluation=this.getById(courseEvaluationStudentQuery.getCourseEvaluationId());
        if(courseEvaluation!=null){
            PageInfo<Student> studentPageInfo=selectCourseService.selectClassStudent(courseEvaluationStudentQuery,courseEvaluation.getCourseClassId());
            List<Student> studentList =studentPageInfo.getList();
            List<CourseEvaluationStudentVo> data = new ArrayList<>();
            for (Student student :studentList) {
                CourseEvaluationStudentVo courseEvaluationStudentVo =new CourseEvaluationStudentVo();
                courseEvaluationStudentVo.setName(student.getName());
                courseEvaluationStudentVo.setSno(student.getSno());
                courseEvaluationStudentVo.setIsEvaluation(Boolean.FALSE);
                SelfEvaluation selfEvaluation=selfEvaluationService.selectOneSelfEvaluation(courseEvaluation.getId(),student.getId());
                if(selfEvaluation!=null){
                    courseEvaluationStudentVo.setSubmitTime(selfEvaluation.getUpdateTime());
                    courseEvaluationStudentVo.setIsEvaluation(Boolean.TRUE);
                }
                data.add(courseEvaluationStudentVo);

            }
            PageInfo<CourseEvaluationStudentVo> result = new PageInfo<>(data);
            BeanUtils.copyProperties(studentPageInfo,result);
            return result;

        }
        return  null;
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
            Integer count = this.selfEvaluationService.countSelfEvaluationById(item.getId());
            Integer classStudentNum = this.selectCourseService.countClassStudent(item.getCourseClassId());
            courseEvaluationVo.setCount(count);
            courseEvaluationVo.setCourseClassCount(classStudentNum);
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
    public boolean saveAnswer(List<CourseTargetVo> answers) {
        if(answers.size()==0){
            return false;
        }
        List<SelfEvaluation> updateData = new ArrayList<>();
        Integer studentId = userManage.getUserInfo().getId();
        for (CourseTargetVo ct:answers) {
            SelfEvaluation selfEvaluation =selfEvaluationService.getById(ct.getSelfEvaluationId());
            if(selfEvaluation==null||!studentId.equals(selfEvaluation.getStudentId())){
                continue;
            }
            this.checkStudentEvaluation(selfEvaluation.getCourseEvaluationId());
            selfEvaluation.setUpdateTime(LocalDateTime.now());
            selfEvaluation.setAnswer(ct.getChoose());
            selfEvaluation.setScore(calcScore(ct.getId(),ct.getChoose()));
            updateData.add(selfEvaluation);
        }
        return selfEvaluationService.updateBatchById(updateData);
    }

    private Integer calcScore(Integer courseTargetId,String choose){
        CourseTarget courseTarget=courseTargetService.getById(courseTargetId);
        String options=courseTarget.getOptions();
        JSONArray jsonArray = JSONArray.fromObject(options);
        List<Options> optionsList = (List<Options>)JSONArray.toCollection(jsonArray,Options.class);
        for (Options op:optionsList) {
            if(op.getPrefix().equals(choose)){
              return Integer.valueOf(op.getScore());
            }
        }

        return 0;
    }

    private void checkStudentEvaluation(Integer courseEvaluationId){
        CourseEvaluation courseEvaluation =this.getById(courseEvaluationId);
        if(courseEvaluation!=null) {
            if (courseEvaluation.getStatus() == 0) {
                throw new TimeException("未到达课程评价开放时间");
            } else if (courseEvaluation.getStatus() == 2) {
                throw new TimeException("课程评价已结束");
            }
        }else {
            throw new EmptyException("不存在课程评价");
        }

    }

    @Override
    public boolean startEvaluation(Integer courseEvaluationId) {
        CourseEvaluation courseEvaluation =this.getById(courseEvaluationId);
        if(courseEvaluation!=null){
            if(courseEvaluation.getStatus()==0){
                throw new TimeException("未到达课程评价开放时间");
            }else if (courseEvaluation.getStatus()==2){
                throw new TimeException("课程评价已结束");
            }
           Integer studentId =  userManage.getUserInfo().getId();
           CourseClass courseClass =courseClassService.getById(courseEvaluation.getCourseClassId());
           SelectCourse selectCourse=  selectCourseService.getSelectCourse(studentId,courseClass.getId());
           if(selectCourse==null){
               throw new EmptyException("你不能参与此场课程评价");
           }
          if(selfEvaluationService.selectOneSelfEvaluation(courseEvaluationId,studentId)!=null){
              return true;
          }
            return selfEvaluationService.createEvaluations(studentId,courseEvaluationId,courseEvaluation.getQuestionnaireId());
        }
        return false;
    }

    @Override
    public List<CourseTargetVo> getQuestions(Integer courseEvaluationId) {
        Integer studentId =userManage.getUserInfo().getId();
        this.checkStudentEvaluation(courseEvaluationId);
        CourseEvaluation courseEvaluation = this.getById(courseEvaluationId);
        List<CourseTargetVo> data =new ArrayList<>();
        if(courseEvaluation==null){ return data;}
        SelfEvaluation selfEvaluation=selfEvaluationService.selectOneSelfEvaluation(courseEvaluationId,studentId);
        if(selfEvaluation!=null){
            List<CourseTarget> courseTargetList=courseTargetService.getByQuestionnaire(courseEvaluation.getQuestionnaireId());
            List<SelfEvaluation> selfEvaluationList =selfEvaluationService.getByStudentIdAndEvaluation(studentId,courseEvaluationId);
            for (int i = 0; i <courseTargetList.size() ; i++) {
                CourseTargetVo courseTargetVo=new CourseTargetVo();
                BeanUtils.copyProperties(courseTargetList.get(i),courseTargetVo);
                courseTargetVo.setChoose(selfEvaluationList.get(i).getAnswer());
                courseTargetVo.setSelfEvaluationId(selfEvaluationList.get(i).getId());
                String options=courseTargetList.get(i).getOptions();
                JSONArray jsonArray = JSONArray.fromObject(options);
                List<Options> optionsList = (List<Options>)JSONArray.toCollection(jsonArray,Options.class);
                for (Options op:optionsList) {
                     op.setScore("");
                }
                courseTargetVo.setOptionsList(optionsList);
                courseTargetVo.setTotalScore(0);
                courseTargetVo.setCreateTime(null);
                courseTargetVo.setUpdateTime(null);
                courseTargetVo.setOptions("");
                data.add(courseTargetVo);
            }
        }
        return data;
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
        if (Duration.between(startTime, endTime).toMinutes() < 30) {
            throw new TimeException("开始时间必须与结束时间有三十分钟以上的间隔");
        }
        questionnaireService.checkQuestionnaire(courseEvaluation.getQuestionnaireId());
    }
}
