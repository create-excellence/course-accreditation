package com.excellent.accreditation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.excellent.accreditation.model.entity.Student;
import com.excellent.accreditation.model.form.StudentQuery;
import com.excellent.accreditation.model.vo.GraduationDemandVo;
import com.excellent.accreditation.model.vo.StudentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author evildoer
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {

    List<StudentVo> queryByCourseClassId(@Param("courseClassId") Integer courseClassId,@Param("sno")String sno,@Param("name")String name);

}
