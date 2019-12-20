package com.excellent.accreditation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.excellent.accreditation.model.entity.GraduationPoint;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author evildoer
 */
@Repository
public interface GraduationPointMapper extends BaseMapper<GraduationPoint> {
    GraduationPoint getVoById(Integer id);

    List<GraduationPoint> pageByQuery(@Param("no") String no, @Param("content") String content);

}
