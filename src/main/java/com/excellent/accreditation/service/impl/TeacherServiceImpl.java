package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.UniqueException;
import com.excellent.accreditation.dao.TeacherMapper;
import com.excellent.accreditation.model.entity.Teacher;
import com.excellent.accreditation.model.form.TeacherQuery;
import com.excellent.accreditation.service.ITeacherService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author evildoer
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Override
    public Teacher getByCode(String code) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teacher::getJno, code);       // 通过工号查询
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public void checkJno(String jno) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teacher::getJno, jno);
        if (super.getOne(queryWrapper) != null) {
            throw new UniqueException("工号不能重复");
        }
    }

    @Override
    public IPage<Teacher> pageByQuery(TeacherQuery teacherQuery) {
        Page<Teacher> page = new Page<>(teacherQuery.getCurrent(), teacherQuery.getSize());
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(teacherQuery.getJno()))
            queryWrapper.like(Teacher::getJno, teacherQuery.getJno());
        if (StringUtils.isNotEmpty(teacherQuery.getTitle()))
            queryWrapper.like(Teacher::getTitle, teacherQuery.getTitle());
        IPage<Teacher> teachers = this.page(page, queryWrapper);
        List<Teacher> list = new ArrayList<>();
        teachers.getRecords().forEach(teacher -> {
            // 隐藏密码
            list.add(teacher.setPassword(Const.PASSWORD));
        });
        teachers.setRecords(list);
        return teachers;
    }

    @Override
    public boolean create(Teacher teacher) {
        teacher.setCreateTime(LocalDateTime.now());
        teacher.setUpdateTime(LocalDateTime.now());
        this.checkJno(teacher.getJno());            // 工号必须唯一
        boolean result = this.save(teacher);

        // 操作成功
        if (result)
            return true;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }
}
