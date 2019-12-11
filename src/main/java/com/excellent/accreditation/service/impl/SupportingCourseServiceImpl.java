package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.dao.SupportingCourseMapper;
import com.excellent.accreditation.model.entity.SupportingCourse;
import com.excellent.accreditation.model.form.SupportingCourseQuery;
import com.excellent.accreditation.model.vo.SupportingCourseVo;
import com.excellent.accreditation.service.ICourseService;
import com.excellent.accreditation.service.IGraduationPointService;
import com.excellent.accreditation.service.ISupportingCourseService;
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
public class SupportingCourseServiceImpl extends ServiceImpl<SupportingCourseMapper, SupportingCourse> implements ISupportingCourseService {

    private final IGraduationPointService graduationPointService;

    private final ICourseService courseService;

    @Autowired
    public SupportingCourseServiceImpl(IGraduationPointService graduationPointService, ICourseService courseService) {
        this.graduationPointService = graduationPointService;
        this.courseService = courseService;
    }

    @Override
    public List<SupportingCourseVo> pageByQuery(SupportingCourseQuery query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        return baseMapper.pageByQuery(query.getCourseName(),query.getGraduationPointContent());
    }

    @Override
    public boolean create(SupportingCourse supportingCourse) {
        this.check(supportingCourse,Const.CREATE);
        supportingCourse.setCreateTime(LocalDateTime.now());
        supportingCourse.setUpdateTime(LocalDateTime.now());
        if(this.save(supportingCourse)){
            return true;
        }
        throw new DatabaseException("未知异常, 数据库操作失败");
    }
    //TODO 待完成
    @Override
    public List<ExcelResult> saveBachByExcel(MultipartFile file) {
        return null;
    }

    @Override
    public void check(SupportingCourse supportingCourse, Integer checkType) {
        if(checkType.equals(Const.CREATE)||supportingCourse.getCourseId()!=null){
            courseService.checkCourse(supportingCourse.getCourseId());
        }
        if(checkType.equals(Const.CREATE)||supportingCourse.getGraduationPointId()!=null){
            graduationPointService.checkGraduationPoint(supportingCourse.getGraduationPointId());
        }

    }

    @Override
    public void checkSupportingCourse(Integer supportingCourseId) {
        if (this.getById(supportingCourseId) == null) {
            throw new ConflictException("支撑课程不存在");
        }
    }

    @Override
    public SupportingCourseVo getVoById(Integer id)
    {
        return baseMapper.getVoById(id);
    }
}
