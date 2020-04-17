package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.*;
import com.excellent.accreditation.dao.SupportingCourseMapper;
import com.excellent.accreditation.model.entity.Course;
import com.excellent.accreditation.model.entity.GraduationPoint;
import com.excellent.accreditation.model.entity.SupportingCourse;
import com.excellent.accreditation.model.form.SupportingCourseQuery;
import com.excellent.accreditation.model.vo.SupportingCourseVo;
import com.excellent.accreditation.service.ICourseService;
import com.excellent.accreditation.service.IGraduationPointService;
import com.excellent.accreditation.service.ISupportingCourseService;
import com.excellent.accreditation.untils.EmptyCheckUtils;
import com.excellent.accreditation.untils.ExcelUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public PageInfo<SupportingCourseVo> pageByQuery(SupportingCourseQuery query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<SupportingCourseVo> supportingCourseVoList=baseMapper.pageByQuery(query.getCourseName(),query.getGraduationPointContent(),query.getGraduationPointId());
        return new PageInfo<>(supportingCourseVoList);
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

    @Override
    public List<ExcelResult> saveBachByExcel(MultipartFile file) {
        List<Map<Integer, String>> list= ExcelUtils.readExcelGetList(file);
        List<ExcelResult> excelResults = new ArrayList<>();
        list.forEach(data -> {
            ExcelResult excelResult = new ExcelResult();
            try {
                EmptyCheckUtils.checkExcelMapAndSetNo(data,excelResult, 3);
                String courseCode = data.get(1);
                String graduationPointNo = data.get(2);
                Double weight = Double.parseDouble(data.get(3));
                SupportingCourse supportingCourse =new SupportingCourse();
                Course course=courseService.getByCode(courseCode);
                GraduationPoint graduationPoint=graduationPointService.getByNo(graduationPointNo);
                if(course==null){
                    throw new ConflictException("课程不存在");
                }
                if(graduationPoint==null){
                    throw new ConflictException("毕业指标点不存在");
                }
                supportingCourse.setCourseId(course.getId());
                supportingCourse.setGraduationPointId(graduationPoint.getId());
                supportingCourse.setWeight(weight);
                if (this.create(supportingCourse)) {
                    excelResult.setStatus(Const.SUCCESS_INCREASE);
                    excelResult.setMessage("添加成功");
                }
            } catch (NumberFormatException e) {
                excelResult.setMessage("无法将部分字段转为数字类型");
            } catch (CommonException e) {
                excelResult.setMessage(e.getMessage());
            }
            excelResults.add(excelResult);
        });
        return excelResults;
    }

    @Override
         public void check(SupportingCourse supportingCourse, Integer checkType) {
            if(checkType.equals(Const.CREATE)||supportingCourse.getCourseId()!=null){
                courseService.checkCourse(supportingCourse.getCourseId());
            }
            if(checkType.equals(Const.CREATE)||supportingCourse.getGraduationPointId()!=null){
                graduationPointService.checkGraduationPoint(supportingCourse.getGraduationPointId());
            }
            if(checkType.equals(Const.CREATE)||supportingCourse.getGraduationPointId()!=null){
                graduationPointService.checkGraduationPoint(supportingCourse.getGraduationPointId());
            }
            this.checkUnique(supportingCourse);

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

    private void checkUnique(SupportingCourse supportingCourse){
        if(supportingCourse.getGraduationPointId()==null&&supportingCourse.getCourseId()==null){
            return;
        }
        if(supportingCourse.getGraduationPointId()==null&&supportingCourse.getCourseId()!=null){
            throw  new EmptyException("毕业指标点不能为空");
        }
        if(supportingCourse.getGraduationPointId()!=null&&supportingCourse.getCourseId()==null){
            throw  new EmptyException("课程不能为空");
        }
        LambdaQueryWrapper<SupportingCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SupportingCourse::getGraduationPointId,supportingCourse.getGraduationPointId());
        queryWrapper.eq(SupportingCourse::getCourseId,supportingCourse.getCourseId());
        SupportingCourse supportingCourse1=this.getOne(queryWrapper);
        if(supportingCourse1!=null&&!supportingCourse1.getId().equals(supportingCourse.getId())){
            throw new UniqueException("已存在相同记录");
        }
    }
}
