package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.*;
import com.excellent.accreditation.dao.SemesterMapper;
import com.excellent.accreditation.model.entity.Semester;
import com.excellent.accreditation.model.form.SemesterQuery;
import com.excellent.accreditation.service.ISemesterService;
import com.excellent.accreditation.untils.DateUtils;
import com.excellent.accreditation.untils.EmptyCheckUtils;
import com.excellent.accreditation.untils.ExcelUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author evildoer
 */
@Service
public class SemesterServiceImpl extends ServiceImpl<SemesterMapper, Semester> implements ISemesterService {

    @Override
    public void checkName(@NonNull String name) {
        LambdaQueryWrapper<Semester> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Semester::getName, name);
        if (super.getOne(queryWrapper) != null) {
            throw new UniqueException("课程代码不能重复");
        }
    }

    @Override
    public boolean create(@NonNull Semester semester) {
        semester.setCreateTime(LocalDateTime.now());
        semester.setUpdateTime(LocalDateTime.now());
        this.checkName(semester.getName());                  // 学期名称必须唯一
        boolean result = this.save(semester);
        // 操作成功
        if (result)
            return true;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }

    @Override
    public PageInfo<Semester> pageByQuery(SemesterQuery query) {
        LambdaQueryWrapper<Semester> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Semester::getStartTime);
        if (StringUtils.isNotEmpty(query.getName()))
            queryWrapper.like(Semester::getName, query.getName());
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<Semester> list = this.list(queryWrapper);
        return new PageInfo<>(list);
    }

    @Override
    public void checkSemester(Integer semester) {
        if (this.getById(semester) == null) {
            throw new ConflictException("学期不存在");
        }
    }

    @Override
    public List<ExcelResult> saveBachByExcel(MultipartFile file) {
        List<Map<Integer, String>> list= ExcelUtils.readExcelGetList(file);
        List<ExcelResult> excelResults = new ArrayList<>();
        list.forEach(data -> {
            ExcelResult excelResult = new ExcelResult();
            try {
                EmptyCheckUtils.checkExcelMapAndSetNo(data,excelResult, 2);
                String name = data.get(1);
                LocalDate startTime = DateUtils.formatExcelDate(Integer.parseInt(data.get(2)));
                Semester semester = new Semester();
                semester.setName(name);
                semester.setStartTime(startTime);
                if (this.create(semester)) {
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
}
