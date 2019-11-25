package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.dao.TeacherMapper;
import com.excellent.accreditation.model.entity.Teacher;
import com.excellent.accreditation.service.ITeacherService;
import org.springframework.stereotype.Service;

/**
 * @Author evildoer
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Override
    public Teacher getByCode(String code) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("jno", code);       // 通过工号查询
        return baseMapper.selectOne(queryWrapper);
    }
}
