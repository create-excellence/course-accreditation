package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.model.entity.GraduationPoint;
import com.excellent.accreditation.model.form.GraduationPointQuery;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author evildoer
 */
public interface IGraduationPointService extends IService<GraduationPoint> {

    boolean create(GraduationPoint graduationPoint);

    PageInfo<GraduationPoint> pageByQuery(GraduationPointQuery graduationPointQuery);

    void checkGraduationPoint(Integer graduationPointId);

    GraduationPoint getByNo(String no);

    void check(GraduationPoint graduationPoint,Integer type);

    List<ExcelResult> saveBachByExcel(MultipartFile file);
}
