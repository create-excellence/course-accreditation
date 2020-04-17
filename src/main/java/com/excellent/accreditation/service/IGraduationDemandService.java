package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.model.entity.GraduationDemand;
import com.excellent.accreditation.model.form.GraduationDemandQuery;
import com.excellent.accreditation.model.vo.GraduationDemandVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author evildoer
 */
public interface IGraduationDemandService extends IService<GraduationDemand> {

    void checkNo(String no);

    void checkGraduationDemand(Integer graduationDemandId);

    PageInfo<GraduationDemandVo> pageByQuery(GraduationDemandQuery graduationDemandQuery);

    boolean create(GraduationDemand graduationDemand);

    GraduationDemand getByNo(String no);

    List<ExcelResult> saveBachByExcel(MultipartFile file);
}
