package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.dao.GraduationPointMapper;
import com.excellent.accreditation.model.entity.GraduationPoint;
import com.excellent.accreditation.model.form.GraduationPointQuery;
import com.excellent.accreditation.service.IGraduationDemandService;
import com.excellent.accreditation.service.IGraduationPointService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author evildoer
 */
@Service
public class GraduationPointServiceImpl extends ServiceImpl<GraduationPointMapper, GraduationPoint> implements IGraduationPointService {

    private final IGraduationDemandService majorService;

    public GraduationPointServiceImpl(IGraduationDemandService majorService) {
        this.majorService = majorService;
    }

    @Override
    public boolean create(GraduationPoint graduationPoint) {
        graduationPoint.setCreateTime(LocalDateTime.now());
        graduationPoint.setUpdateTime(LocalDateTime.now());
        majorService.checkGraduationDemand(graduationPoint.getGraduationDemandId());     // 查看是否有对应的毕业要求
        boolean result = this.save(graduationPoint);
        // 操作成功
        if (result)
            return true;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }

    @Override
    public PageInfo<GraduationPoint> pageByQuery(GraduationPointQuery query) {
        LambdaQueryWrapper<GraduationPoint> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(query.getNo()))
            queryWrapper.like(GraduationPoint::getNo, query.getNo());
        if (StringUtils.isNotEmpty(query.getContent()))
            queryWrapper.like(GraduationPoint::getContent, query.getContent());
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<GraduationPoint> list = this.list(queryWrapper);
        PageInfo<GraduationPoint> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
