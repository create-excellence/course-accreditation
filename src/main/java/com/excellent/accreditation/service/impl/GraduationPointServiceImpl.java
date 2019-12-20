package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.UniqueException;
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

    private final IGraduationDemandService graduationDemandService;

    public GraduationPointServiceImpl(IGraduationDemandService graduationDemandService) {
        this.graduationDemandService = graduationDemandService;
    }

    @Override
    public boolean create(GraduationPoint graduationPoint) {
        this.check(graduationPoint,Const.CREATE);
        graduationPoint.setCreateTime(LocalDateTime.now());
        graduationPoint.setUpdateTime(LocalDateTime.now());
        boolean result = this.save(graduationPoint);
        // 操作成功
        if (result)
            return true;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }

    @Override
    public PageInfo<GraduationPoint> pageByQuery(GraduationPointQuery query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<GraduationPoint> list = baseMapper.pageByQuery(query.getNo(),query.getContent());
        return new PageInfo<>(list);
    }

    @Override
    public void checkGraduationPoint(Integer graduationPointId) {
        if (this.getById(graduationPointId) == null) {
            throw new ConflictException("毕业指标点不存在");
        }
    }

    @Override
    public GraduationPoint getByNo(String no) {
        LambdaQueryWrapper<GraduationPoint> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(GraduationPoint::getNo,no);
        GraduationPoint graduationPoint=this.getOne(queryWrapper);
        if(graduationPoint==null){
            throw new ConflictException("毕业指标点不存在");
        }
        return graduationPoint;
    }

    @Override
    public void check(GraduationPoint graduationPoint, Integer type) {
        if (type.equals(Const.CREATE) || graduationPoint.getNo() != null) {
            this.checkNo(graduationPoint.getNo(), graduationPoint.getId());
        }
        if (type.equals(Const.CREATE) || graduationPoint.getGraduationDemandId() != null) {
            graduationDemandService.checkGraduationDemand(graduationPoint.getGraduationDemandId());     // 查看是否有对应的毕业要求
        }
    }

    private void  checkNo(String no,Integer id){
        LambdaQueryWrapper<GraduationPoint> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GraduationPoint::getNo, no);
        GraduationPoint graduationPoint = super.getOne(queryWrapper);
        if (graduationPoint != null && !graduationPoint.getId().equals(id)) {
            // 如果code已存在还要检查是否当前更新的是否为同一条记录,若不同则抛出异常
            throw new UniqueException("指标点编号不能重复");
        }
    }
}
