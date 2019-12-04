package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.model.entity.Major;
import com.excellent.accreditation.model.form.MajorQuery;
import com.github.pagehelper.PageInfo;

/**
 * @Author evildoer
 */
public interface IMajorService extends IService<Major> {

    void checkCode(String code);

    void checkMajor(Integer majorId);

    PageInfo<Major> pageByQuery(MajorQuery majorQuery);

    boolean create(Major major);
}
