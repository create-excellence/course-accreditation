package com.excellent.accreditation.manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.dao.CourseTargetMapper;
import com.excellent.accreditation.dao.GraduationDemandMapper;
import com.excellent.accreditation.model.entity.CourseTarget;
import com.excellent.accreditation.model.entity.Student;
import com.excellent.accreditation.model.entity.Teacher;
import com.excellent.accreditation.model.vo.UserVo;
import com.excellent.accreditation.service.IStudentService;
import com.excellent.accreditation.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName UserManage
 * @Description TODO
 * @Author 安羽兮
 * @Date 2019/11/2516:22
 * @Version 1.0
 **/
public class UserManage {

    private final IStudentService studentService;

    private final ITeacherService teacherService;

    @Autowired
    CourseTargetMapper courseTargetMapper;



    public UserManage(IStudentService studentService,
                      ITeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    /**
     * @Author 安羽兮
     * @Description 登录
     * @Date 19:10 2019/11/25
     * @Param [code, password]
     * @Return com.excellent.accreditation.model.vo.UserVo
     **/
    public UserVo login(String code, String password) {
        CourseTarget courseTarget=courseTargetMapper.selectById(1);
        Student student = studentService.getByCode(code);
        Teacher teacher = teacherService.getByCode(code);
        if (student != null) {          // 学生登录
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("sno", code)       // 通过学号登录
                    .eq("password", password);
            Student s = studentService.getOne(queryWrapper);
            return UserVo.convert(s);
        } else if (teacher != null) {  // 教师登录
            QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("jno", code)       // 通过工号登录
                    .eq("password", password);
            Teacher t = teacherService.getOne(queryWrapper);
            return UserVo.convert(t);
        }
        // 登录失败
        return null;
    }

    /**
     * @Author 安羽兮
     * @Description 注册
     * @Date 19:10 2019/11/25
     * @Param [code, password, role]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    public ServerResponse register(String code, String password, String role) {
        if (role.equals(Const.STUDENT)) {          // 学生注册
            Student student = studentService.getByCode(code);
            // 学号不能重复
            if (student == null) {
                Student s = new Student();
                s.setSno(code);
                s.setPassword(password);
                studentService.saveOrUpdate(s);
                return ServerResponse.createByErrorMessage("注册成功");
            } else {
                return ServerResponse.createByErrorMessage("此学号已被注册");
            }
        } else if (role.equals(Const.TEACHER)) {  // 教师注册
            Teacher teacher = teacherService.getByCode(code);
            // 工号不能重复
            if (teacher == null) {
                Teacher t = new Teacher();
                t.setJno(code);
                t.setPassword(password);
                teacherService.saveOrUpdate(t);
                return ServerResponse.createByErrorMessage("注册成功");
            } else {
                return ServerResponse.createByErrorMessage("此工号已被注册");
            }
        }
        // 登录失败
        return ServerResponse.createByErrorMessage("注册失败");
    }
}
