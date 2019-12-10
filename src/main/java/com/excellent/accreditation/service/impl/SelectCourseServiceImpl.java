package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.ExcelException;
import com.excellent.accreditation.common.exception.UniqueException;
import com.excellent.accreditation.dao.SelectCourseMapper;
import com.excellent.accreditation.model.entity.SelectCourse;
import com.excellent.accreditation.model.form.SelectCourseQuery;
import com.excellent.accreditation.model.vo.SelectCourseVo;
import com.excellent.accreditation.service.ISelectCourseService;
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
public class SelectCourseServiceImpl extends ServiceImpl<SelectCourseMapper, SelectCourse> implements ISelectCourseService {


    @Autowired
    SelectCourseMapper selectCourseMapper;

    @Override
    public boolean create(SelectCourse selectCourse) {
        selectCourse.setCreateTime(LocalDateTime.now());
        selectCourse.setUpdateTime(LocalDateTime.now());
        boolean result = this.save(selectCourse);
        // 操作成功
        if (result)
            return true;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }


    @Override
    public List<ExcelResult> saveBachByExcel(MultipartFile file) {
        List<Map<Integer, String>> list= ExcelUtils.readExcelGetList(file);
        List<ExcelResult> excelResults = new ArrayList<>();
        list.forEach(data -> {
            ExcelResult excelResult = new ExcelResult();
            try {
                EmptyCheckUtils.checkExcelMapAndSetNo(data,excelResult, 2);
                String courseClassId = data.get(1);
                String studentId = data.get(2);
                Integer courseClassId1 = Integer.valueOf(courseClassId);
                Integer studentId1 = Integer.valueOf(studentId);
                this.checkSelectCourse(courseClassId1,studentId1);
                SelectCourse selectCourse = new SelectCourse();
                selectCourse.setCourseClassId(courseClassId1);
                selectCourse.setStudentId(studentId1);
                selectCourse.setCreateTime(LocalDateTime.now());
                selectCourse.setUpdateTime(LocalDateTime.now());
                if (super.save(selectCourse)) {
                    excelResult.setStatus(Const.SUCCESS_INCREASE);
                    excelResult.setMessage("添加成功");
                }
            } catch (NumberFormatException e) {
                excelResult.setMessage("无法将部分字段转为数字类型");
            } catch (UniqueException | ExcelException e) {
                excelResult.setMessage(e.getMessage());
            }
            excelResults.add(excelResult);
        });
        return excelResults;
    }

    @Override
    public void checkSelectCourse(Integer courseClassId, Integer studentId) {
        LambdaQueryWrapper<SelectCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SelectCourse::getCourseClassId, courseClassId);
        queryWrapper.eq(SelectCourse::getStudentId, studentId);
        if (this.getOne(queryWrapper)  != null ) {
            throw new UniqueException("选课已存在");
        }
    }

    @Override
    public SelectCourseVo selectCourseId(Integer id) {
        return selectCourseMapper.selectCourseId(id);
    }

    /**
     *@author pu
     *@data 2019/12/10
     *description:
     */
    @Override
    public PageInfo<SelectCourseVo> pageByQuery(SelectCourseQuery selectCourseQuery) {
        PageHelper.startPage(selectCourseQuery.getPage(), selectCourseQuery.getPageSize());
        List<SelectCourseVo> list =selectCourseMapper.pageByQuery(selectCourseQuery.getStudent(),selectCourseQuery.getTeacher(),selectCourseQuery.getSemester(),selectCourseQuery.getCourse());
        return new PageInfo<>(list);
    }
}
