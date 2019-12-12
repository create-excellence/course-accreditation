package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.UniqueException;
import com.excellent.accreditation.dao.GraduationDemandMapper;
import com.excellent.accreditation.model.entity.GraduationDemand;
import com.excellent.accreditation.model.form.GraduationDemandQuery;
import com.excellent.accreditation.model.vo.GraduationDemandVo;
import com.excellent.accreditation.service.IGraduationDemandService;
import com.excellent.accreditation.service.IMajorService;
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
public class GraduationDemandServiceImpl extends ServiceImpl<GraduationDemandMapper, GraduationDemand> implements IGraduationDemandService {

    private final GraduationDemandMapper graduationDemandMapper;

    private final IMajorService majorService;

    public GraduationDemandServiceImpl(GraduationDemandMapper graduationDemandMapper,
                                       IMajorService majorService) {
        this.graduationDemandMapper = graduationDemandMapper;
        this.majorService = majorService;
    }

    @Override
    public void checkCode(@NonNull String code) {
        LambdaQueryWrapper<GraduationDemand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GraduationDemand::getNo, code);
        if (super.getOne(queryWrapper) != null) {
            throw new UniqueException("毕业要求编号不能重复");
        }
    }

    @Override
    public void checkGraduationDemand(Integer graduationDemandId) {
        if (this.getById(graduationDemandId) == null) {
            throw new ConflictException("毕业要求不存在");
        }
    }

    @Override
    public boolean create(GraduationDemand graduationDemand) {
        graduationDemand.setUpdateTime(LocalDateTime.now());
        graduationDemand.setCreateTime(LocalDateTime.now());
        this.checkCode(graduationDemand.getNo());                  // 毕业要求编号必须唯一
        majorService.checkMajor(graduationDemand.getMajorId());     // 查看是否有对应的专业
        boolean result = this.save(graduationDemand);
        // 操作成功
        if (result)
            return true;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }

    @Override
    public PageInfo<GraduationDemandVo> pageByQuery(GraduationDemandQuery query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<GraduationDemandVo> list = graduationDemandMapper.pageByQuery(query.getMajor(), query.getNo());
        return new PageInfo<>(list);
    }
}
