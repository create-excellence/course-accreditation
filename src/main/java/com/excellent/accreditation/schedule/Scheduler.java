package com.excellent.accreditation.schedule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.model.entity.CourseEvaluation;
import com.excellent.accreditation.service.ICourseEvaluationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Scheduler {
    @Autowired
    ICourseEvaluationService courseEvaluationService;

    //每分钟执行一次
    @Scheduled(cron = "0 */1 * * * ?")
    public void startEvaluation() {
        LambdaQueryWrapper<CourseEvaluation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseEvaluation::getStatus, Const.NOT_STARTED);
        List<CourseEvaluation> list =courseEvaluationService.list(queryWrapper);
        for (CourseEvaluation courseEvaluation:list) {
            if(LocalDateTime.now().compareTo(courseEvaluation.getStartTime())>0){
                courseEvaluation.setStatus(Const.IN_PROGRESS);
                courseEvaluationService.saveOrUpdate(courseEvaluation);
            }
        }
    }

    //每分钟执行一次
    @Scheduled(cron = "0 */1 * * * ?")
    public void finishEvaluation() {
        LambdaQueryWrapper<CourseEvaluation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseEvaluation::getStatus, Const.IN_PROGRESS);
        List<CourseEvaluation> list =courseEvaluationService.list(queryWrapper);
        for (CourseEvaluation courseEvaluation:list) {
            if(LocalDateTime.now().compareTo(courseEvaluation.getEndTime())>0){
                courseEvaluation.setStatus(Const.THE_END);
                courseEvaluationService.saveOrUpdate(courseEvaluation);
            }
        }
    }





}