package com.excellent.accreditation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.excellent.accreditation.model.entity.GraduationDemand;
import com.excellent.accreditation.model.vo.GraduationDemandVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author evildoer
 */
@Repository
public interface GraduationDemandMapper extends BaseMapper<GraduationDemand> {
    List<GraduationDemandVo> pageByQuery(@Param("major") String major, @Param("no") String no);
}
