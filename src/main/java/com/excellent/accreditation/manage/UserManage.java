package com.excellent.accreditation.manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.excellent.accreditation.common.authentication.JWTUtil;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.common.exception.AuthenticationException;
import com.excellent.accreditation.model.entity.Student;
import com.excellent.accreditation.model.entity.Teacher;
import com.excellent.accreditation.model.vo.UserVo;
import com.excellent.accreditation.service.IStudentService;
import com.excellent.accreditation.service.ITeacherService;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
        Student student = studentService.getByCode(code);
        Teacher teacher = teacherService.getByCode(code);
        if (student != null) {          // 学生登录
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("sno", code)       // 通过学号登录
                    .eq("password", password);
            Student s = studentService.getOne(queryWrapper);
            String token = JWTUtil.encryptToken(JWTUtil.sign(code, password));
            return UserVo.convert(s, token);
        } else if (teacher != null) {  // 教师登录
            QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("jno", code)       // 通过工号登录
                    .eq("password", password);
            Teacher t = teacherService.getOne(queryWrapper);
            String token = JWTUtil.encryptToken(JWTUtil.sign(code, password));
            UserVo userVo = UserVo.convert(t, token);
            userVo.setRole(this.getRoleByCode(code));
            return userVo;
        }
        // 登录失败
        return null;
    }

    /**
     * @Author 安羽兮
     * @Description 通过token获取用户信息
     * @Date 11:33 2019/12/7
     * @Param []
     * @Return com.excellent.accreditation.model.vo.UserVo
     **/
    public UserVo getUserInfo() {
        String code = getCodeByToken();
        String role = getRoleByCode(code);

        if (Const.STUDENT.equals(role)) {
            // 学生
            Student student = studentService.getByCode(code);
            // 重新签署token
            String token = JWTUtil.encryptToken(JWTUtil.sign(code, student.getPassword()));
            UserVo userVo = UserVo.convert(student, token);
            userVo.setRole(role);
            return userVo;
        } else {
            // 老师或管理员
            Teacher teacher = teacherService.getByCode(code);
            // 重新签署token
            String token = JWTUtil.encryptToken(JWTUtil.sign(code, teacher.getPassword()));
            UserVo userVo = UserVo.convert(teacher, token);
            userVo.setRole(role);
            return userVo;
        }
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
        // 注册失败
        return ServerResponse.createByErrorMessage("注册失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过token获取code
     * @Date 19:45 2019/12/4
     * @Param []
     * @Return java.lang.String
     **/
    public String getCodeByToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Const.TOKEN);
        if (StringUtils.isEmpty(token))
            throw new AuthenticationException("请登录后访问！");
        token = JWTUtil.decryptToken(token);            // 解密token
        return JWTUtil.getName(token);
    }

    /**
     * @Author 安羽兮
     * @Description 通过code判断用户角色
     * @Date 19:45 2019/12/4
     * @Param [code]
     * @Return java.lang.String
     **/
    public String getRoleByCode(String code) {
        Student student = studentService.getByCode(code);
        Teacher teacher = teacherService.getByCode(code);
        if (student != null) {
            return Const.STUDENT;
        } else if (teacher != null) {
            // 教师的Role为管理员
            if (Const.ADMIN.equals(teacher.getRole()))
                return Const.ADMIN;
            return Const.TEACHER;
        }
        return null;
    }
}
