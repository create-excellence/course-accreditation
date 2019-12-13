package com.excellent.accreditation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.excellent.accreditation.model.entity.SupportingCourse;
import com.excellent.accreditation.model.vo.SelectCourseVo;
import com.excellent.accreditation.model.vo.SupportingCourseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author evildoer
 */
@Repository
public interface SupportingCourseMapper extends BaseMapper<SupportingCourse> {

    SupportingCourseVo getVoById(Integer id);

    List<SupportingCourseVo> pageByQuery (String course, String graduationPoint);
}
