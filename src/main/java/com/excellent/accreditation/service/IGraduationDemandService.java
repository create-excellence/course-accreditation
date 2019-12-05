package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.model.entity.GraduationDemand;
import com.excellent.accreditation.model.form.GraduationDemandQuery;
import com.github.pagehelper.PageInfo;

/**
 * @Author evildoer
 */
public interface IGraduationDemandService extends IService<GraduationDemand> {

    void checkCode(String code);

    void checkGraduationDemand(Integer graduationDemandId);

    PageInfo<GraduationDemand> pageByQuery(GraduationDemandQuery graduationDemandQuery);

    boolean create(GraduationDemand graduationDemand);
}
