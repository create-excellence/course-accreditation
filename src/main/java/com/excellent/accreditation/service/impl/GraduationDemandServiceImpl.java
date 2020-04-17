package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.CommonException;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.UniqueException;
import com.excellent.accreditation.dao.GraduationDemandMapper;
import com.excellent.accreditation.model.entity.GraduationDemand;
import com.excellent.accreditation.model.entity.Major;
import com.excellent.accreditation.model.form.GraduationDemandQuery;
import com.excellent.accreditation.model.vo.GraduationDemandVo;
import com.excellent.accreditation.service.IGraduationDemandService;
import com.excellent.accreditation.service.IMajorService;
import com.excellent.accreditation.untils.EmptyCheckUtils;
import com.excellent.accreditation.untils.ExcelUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.lang.NonNull;
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
public class GraduationDemandServiceImpl extends ServiceImpl<GraduationDemandMapper, GraduationDemand> implements IGraduationDemandService {

    private final GraduationDemandMapper graduationDemandMapper;

    private final IMajorService majorService;

    public GraduationDemandServiceImpl(GraduationDemandMapper graduationDemandMapper,
                                       IMajorService majorService) {
        this.graduationDemandMapper = graduationDemandMapper;
        this.majorService = majorService;
    }

    @Override
    public void checkNo(@NonNull String no) {
        if (this.getByNo(no) != null) {
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
        this.checkNo(graduationDemand.getNo());                  // 毕业要求编号必须唯一
        majorService.checkMajor(graduationDemand.getMajorId());     // 查看是否有对应的专业
        boolean result = this.save(graduationDemand);
        // 操作成功
        if (result)
            return true;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }

    @Override
    public GraduationDemand getByNo(String no) {
        LambdaQueryWrapper<GraduationDemand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GraduationDemand::getNo, no);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<ExcelResult> saveBachByExcel(MultipartFile file) {
        List<Map<Integer, String>> list = ExcelUtils.readExcelGetList(file);
        List<ExcelResult> excelResults = new ArrayList<>();
        list.forEach(data -> {
            ExcelResult excelResult = new ExcelResult();
            try {
                EmptyCheckUtils.checkExcelMapAndSetNo(data, excelResult, 3);
                String no = data.get(1);
                String content = data.get(2);
                Major major = majorService.getByCode(data.get(3));
                if(major==null){
                    throw new ConflictException("专业不存在");
                }
                GraduationDemand graduationDemand =new GraduationDemand();
                graduationDemand.setNo(no);
                graduationDemand.setContent(content);
                graduationDemand.setMajorId(major.getId());
                if (this.create(graduationDemand)) {
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

    @Override
    public PageInfo<GraduationDemandVo> pageByQuery(GraduationDemandQuery query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<GraduationDemandVo> list = graduationDemandMapper.pageByQuery(query.getMajor(), query.getNo());
        return new PageInfo<>(list);
    }
}
