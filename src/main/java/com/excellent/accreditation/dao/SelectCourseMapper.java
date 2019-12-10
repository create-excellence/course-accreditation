package com.excellent.accreditation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.excellent.accreditation.model.entity.SelectCourse;
import com.excellent.accreditation.model.vo.SelectCourseVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*@author pu
*@data 2019/12/10
*description:
*/
@Repository
public interface SelectCourseMapper extends BaseMapper<SelectCourse> {

    List<SelectCourseVo> pageByQuery (String student,String teacher,String semester,String course);

    SelectCourseVo selectCourseId(Integer id);

}
