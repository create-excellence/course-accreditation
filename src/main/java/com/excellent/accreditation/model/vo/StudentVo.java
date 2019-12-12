package com.excellent.accreditation.model.vo;

import com.excellent.accreditation.model.entity.Student;
import lombok.Data;

/**
 * @ClassName StudentVo
 * @Description TODO
 * @Author 安羽兮
 * @Date 2019/12/513:50
 * @Version 1.0
 **/
@Data
public class StudentVo extends Student {

    private String major;

    public static StudentVo convert(Student student) {
        StudentVo studentVo = new StudentVo();
        studentVo.setId(student.getId());
        studentVo.setSno(student.getSno());
        studentVo.setName(student.getName());
        studentVo.setSex(student.getSex());
        studentVo.setGrade(student.getGrade());
        studentVo.setStatus(student.getStatus());
        studentVo.setMajorId(student.getMajorId());
        studentVo.setLoginTime(student.getLoginTime());
        studentVo.setUpdateTime(student.getUpdateTime());
        studentVo.setCreateTime(student.getCreateTime());
        return studentVo;
    }
}
