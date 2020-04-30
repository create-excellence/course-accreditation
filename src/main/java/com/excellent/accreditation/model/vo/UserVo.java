package com.excellent.accreditation.model.vo;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.excellent.accreditation.model.entity.Student;
import com.excellent.accreditation.model.entity.Teacher;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName UserVo
 * @Description TODO
 * @Author 安羽兮
 * @Date 2019/11/2516:12
 * @Version 1.0
 **/
@Data
public class UserVo {

    private static final String DEFAULT_AVATAR = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";

    private static final long serialVersionUID = 1L;

    private Integer id;

    private List<String> role;

    private String name;

    private String sex;

    private String avatar;

    private String title;


    private LocalDate birth;

    private String graduateSchool;

    private String graduateMajor;



    /**
     * 正常-0
     */
    private Integer status;

    private String token;

    private LocalDateTime loginTime;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    public static UserVo convert(Student student, String token) {
        UserVo userVo = convert(student);
        userVo.setToken(token);
        return userVo;
    }

    public static UserVo convert(Teacher teacher, String token) {
        UserVo userVo = convert(teacher);
        userVo.setToken(token);
        return userVo;
    }

    public static UserVo convert(Student student) {
        UserVo userVo = new UserVo();
        userVo.id = student.getId();
        userVo.name = student.getName();
        userVo.sex = student.getSex();
        userVo.avatar = StringUtils.isNotEmpty(student.getAvatar()) ? student.getAvatar() : DEFAULT_AVATAR;
        userVo.status = 0;
        userVo.updateTime = student.getUpdateTime();
        userVo.createTime = student.getCreateTime();
        return userVo;
    }

    public static UserVo convert(Teacher teacher) {
        UserVo userVo = new UserVo();
        userVo.id = teacher.getId();
        userVo.sex = teacher.getSex();
        userVo.name = teacher.getName();
        userVo.title = teacher.getTitle();
        userVo.birth = teacher.getBirth();
        userVo.graduateSchool = teacher.getGraduateSchool();
        userVo.graduateMajor = teacher.getGraduateMajor();
        userVo.avatar = StringUtils.isNotEmpty(teacher.getAvatar()) ? teacher.getAvatar() : DEFAULT_AVATAR;
        userVo.status = 0;
        userVo.updateTime = teacher.getUpdateTime();
        userVo.createTime = teacher.getCreateTime();
        return userVo;
    }
}
