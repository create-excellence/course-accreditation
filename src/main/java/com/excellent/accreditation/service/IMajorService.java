package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.model.entity.Major;
import com.excellent.accreditation.model.form.MajorQuery;

/**
 * @Author evildoer
 */
public interface IMajorService extends IService<Major> {

    void checkCode(String code);

    void checkMajor(Integer majorId);

    IPage<Major> pageByQuery(MajorQuery majorQuery);

    boolean create(Major major);
}
