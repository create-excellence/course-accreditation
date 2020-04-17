package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.*;
import com.excellent.accreditation.dao.MajorMapper;
import com.excellent.accreditation.model.entity.Course;
import com.excellent.accreditation.model.entity.Major;
import com.excellent.accreditation.model.form.MajorQuery;
import com.excellent.accreditation.service.IMajorService;
import com.excellent.accreditation.untils.EmptyCheckUtils;
import com.excellent.accreditation.untils.ExcelUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author evildoer
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements IMajorService {

    @Override
    public void checkCode(String code,Integer majorId) {
        LambdaQueryWrapper<Major> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Major::getCode, code);
        Major major=this.getByCode(code);
        if ( major!= null&&!major.getId().equals(majorId)) {
            throw new UniqueException("专业代码已存在");
        }
    }

    @Override
    public void checkMajor(Integer majorId) {
        if (this.getById(majorId) == null) {
            throw new ConflictException("专业不存在");
        }
    }

    @Override
    public Major getByCode(String code) {
        LambdaQueryWrapper<Major> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Major::getCode, code);
        return this.getOne(queryWrapper);
    }

    @Override
    public PageInfo<Major> pageByQuery(MajorQuery query) {
        LambdaQueryWrapper<Major> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(query.getCode()))
            queryWrapper.like(Major::getCode, query.getCode());
        if (StringUtils.isNotEmpty(query.getName()))
            queryWrapper.like(Major::getName, query.getName());
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<Major> list = this.list(queryWrapper);
        return new PageInfo<>(list);
    }

    @Override
    public boolean create(Major major) {
        this.checkCode(major.getCode(),null);                  // 专业代码必须唯一
        major.setCreateTime(LocalDateTime.now());
        major.setUpdateTime(LocalDateTime.now());
        boolean result = this.save(major);
        // 操作成功
        if (result)
            return true;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }

    @Override
    public List<ExcelResult> saveBachByExcel(MultipartFile file) {
        List<Map<Integer, String>> list=ExcelUtils.readExcelGetList(file);
        List<ExcelResult> excelResults = new ArrayList<>();
        list.forEach(data -> {
            ExcelResult excelResult = new ExcelResult();
            try {
                EmptyCheckUtils.checkExcelMapAndSetNo(data,excelResult, 2);
                String name = data.get(1);
                String code = data.get(2);
                Major major = new Major();
                major.setCode(code);
                major.setName(name);
                if (this.create(major)) {
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
