package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.UniqueException;
import com.excellent.accreditation.dao.SemesterMapper;
import com.excellent.accreditation.model.entity.Semester;
import com.excellent.accreditation.model.form.SemesterQuery;
import com.excellent.accreditation.service.ISemesterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
            return result;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }

    @Override
    public PageInfo<Semester> pageByQuery(SemesterQuery query) {
        LambdaQueryWrapper<Semester> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(query.getSemester()))
            queryWrapper.like(Semester::getName, query.getSemester());
        PageHelper.startPage(query.getCurrent(), query.getSize());
        List<Semester> list = this.list(queryWrapper);
        PageInfo<Semester> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
