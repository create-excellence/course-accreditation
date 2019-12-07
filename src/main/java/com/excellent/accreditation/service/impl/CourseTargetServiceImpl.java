package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.dao.CourseTargetMapper;
import com.excellent.accreditation.model.entity.CourseClass;
import com.excellent.accreditation.model.entity.CourseTarget;
import com.excellent.accreditation.model.form.CourseTargetQuery;
import com.excellent.accreditation.service.ICourseTargetService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author evildoer
 */
@Service
public class CourseTargetServiceImpl extends ServiceImpl<CourseTargetMapper, CourseTarget> implements ICourseTargetService {

    @Override
    public void checkCourseTarget(Integer courseTargetId) {
        if (this.getById(courseTargetId) == null) {
            throw new ConflictException("课程目标不存在");
        }
    }

    @Override
    public PageInfo<CourseTarget> pageByQuery(CourseTargetQuery query) {
        return null;
    }

    @Override
    public boolean create(CourseTarget courseTarget) {
        this.check(courseTarget, Const.CREATE);
        return false;
    }

    @Override
    public List<ExcelResult> saveBachByExcel(MultipartFile file) {
        return null;
    }

    @Override
    public void check(CourseTarget courseTarget, Integer type) {

    }

}
