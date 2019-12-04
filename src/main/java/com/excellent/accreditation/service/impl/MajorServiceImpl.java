package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.exception.ConfictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.UniqueException;
import com.excellent.accreditation.dao.MajorMapper;
import com.excellent.accreditation.model.entity.Major;
import com.excellent.accreditation.model.form.MajorQuery;
import com.excellent.accreditation.service.IMajorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author evildoer
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements IMajorService {

    @Override
    public void checkCode(String code) {
        LambdaQueryWrapper<Major> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Major::getCode, code);
        if (this.getOne(queryWrapper) != null) {
            throw new UniqueException("专业代码已存在");
        }
    }

    @Override
    public void checkMajor(Integer majorId) {
        if (this.getById(majorId) == null) {
            throw new ConfictException("专业不存在");
        }
    }

    @Override
    public IPage<Major> pageByQuery(MajorQuery majorQuery) {
        Page<Major> page = new Page<>(majorQuery.getCurrent(), majorQuery.getSize());
        LambdaQueryWrapper<Major> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(majorQuery.getCode()))
            queryWrapper.like(Major::getCode, majorQuery.getCode());
        if (StringUtils.isNotEmpty(majorQuery.getName()))
            queryWrapper.like(Major::getName, majorQuery.getName());
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean create(Major major) {
        this.checkCode(major.getCode());                  // 专业代码必须唯一
        major.setCreateTime(LocalDateTime.now());
        major.setUpdateTime(LocalDateTime.now());
        boolean result = this.save(major);
        // 操作成功
        if (result)
            return result;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }
}
