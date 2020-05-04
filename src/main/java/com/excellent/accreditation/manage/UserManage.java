package com.excellent.accreditation.manage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.excellent.accreditation.common.authentication.JWTUtil;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.common.exception.AuthenticationException;
import com.excellent.accreditation.common.exception.EmptyException;
import com.excellent.accreditation.model.entity.Major;
import com.excellent.accreditation.model.entity.Role;
import com.excellent.accreditation.model.entity.Student;
import com.excellent.accreditation.model.entity.Teacher;
import com.excellent.accreditation.model.vo.UserVo;
import com.excellent.accreditation.service.IMajorService;
import com.excellent.accreditation.service.IRoleService;
import com.excellent.accreditation.service.IStudentService;
import com.excellent.accreditation.service.ITeacherService;
import com.excellent.accreditation.untils.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserManage
 * @Description TODO
 * @Author 安羽兮
 * @Date 2019/11/2516:22
 * @Version 1.0
 **/
@Component
public class UserManage {

    private final IRoleService roleService;

    private final IStudentService studentService;

    private final ITeacherService teacherService;

    private final IMajorService majorService;


    @Value("${upload.picture.path}")
    private String uploadPicturePath;



    public UserManage(IRoleService roleService,
                      IStudentService studentService,
                      ITeacherService teacherService, IMajorService majorService) {
        this.roleService = roleService;
        this.studentService = studentService;
        this.majorService=majorService;
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
            if(s==null) throw new EmptyException("账号或密码错误");
            s.setLoginTime(LocalDateTime.now());
            studentService.saveOrUpdate(s);
            String token = JWTUtil.encryptToken(JWTUtil.sign(code, password));
            UserVo userVo = UserVo.convert(s, token);

            userVo.setRole(this.getRolesByCode(code));
            return userVo;
        } else if (teacher != null) {  // 教师登录
            QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("jno", code)       // 通过工号登录
                    .eq("password", password);
            Teacher t = teacherService.getOne(queryWrapper);
            if(t==null) throw new EmptyException("账号或密码错误");
            t.setLoginTime(LocalDateTime.now());
            teacherService.saveOrUpdate(t);
            String token = JWTUtil.encryptToken(JWTUtil.sign(code, password));
            UserVo userVo = UserVo.convert(t, token);
            userVo.setRole(this.getRolesByCode(code));
            return userVo;
        }
        // 登录失败
        return null;
    }

    /**
     * @Author 安羽兮
     * @Description //TODO
     * @Date 9:54 2020/3/26
     * @Param [oldPassword, newPassword]
     * @Return boolean
     **/
    public ServerResponse changePassword(String oldPassword, String newPassword) {
        String code = this.getCodeByToken();
        Student student = studentService.getByCode(code);
        Teacher teacher = teacherService.getByCode(code);
        boolean result = false;
        if (student != null) {          // 学生
            studentService.updatePassword(code, oldPassword, newPassword);
        } else if (teacher != null) {  // 教师
            teacherService.updatePassword(code, oldPassword, newPassword);
        }
        return ServerResponse.createBySuccessMessage("密码修改成功");
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
        Student student = studentService.getByCode(code);
        Teacher teacher = teacherService.getByCode(code);
        List<String> roles = getRolesByCode(code);

        if (student != null) {
            if (roles.isEmpty()) {
                roles.add(Const.STUDENT);
            }
            // 重新签署token
            String token = JWTUtil.encryptToken(JWTUtil.sign(code, student.getPassword()));
            UserVo userVo = UserVo.convert(student, token);
            userVo.setRole(roles);
            return userVo;
        } else if (teacher != null) {
            if (roles.isEmpty()) {
                roles.add(Const.TEACHER);
            }
            // 重新签署token
            String token = JWTUtil.encryptToken(JWTUtil.sign(code, teacher.getPassword()));
            UserVo userVo = UserVo.convert(teacher, token);
            userVo.setRole(roles);
            return userVo;
        }
        throw new AuthenticationException("用户token异常！");
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
     * @Description 通过token获取role集合
     * @Date 19:45 2019/12/4
     * @Param []
     * @Return java.lang.String
     **/
    public List<String> getRolesByToken() {
        String code = this.getCodeByToken();
        return getRolesByCode(code);
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
        String code = JWTUtil.getName(token);
        Student student = studentService.getByCode(code);
        Teacher teacher = teacherService.getByCode(code);
        String password = null;
        if (student != null) {
            password = student.getPassword();
        } else if (teacher != null) {
            password = teacher.getPassword();
        }
        // 校验token是否合法
        if (!JWTUtil.verify(token, code, password))
            throw new AuthenticationException("token校验不通过");
        return code;
    }

    /**
     * @Author 安羽兮
     * @Description 通过code判断用户角色
     * @Date 19:45 2019/12/4
     * @Param [code]
     * @Return java.lang.String
     **/
    public List<String> getRolesByCode(String code) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getCode, code);
        List<String> roles = new ArrayList<>();
        List<Role> list = roleService.list(queryWrapper);
        if (list == null) {
            throw new AuthenticationException("用户未分配角色, 请联系管理员！");
        }
        list.forEach(r -> {
            roleService.checkRole(r.getRole());
            roles.add(r.getRole());
        });
        return roles;
    }


    /**
     * @Author ashe
     * @Description 通过UserVo修改信息
     * @Date 19:45 2019/12/4
     * @Param [userVo]
     * @Return java.lang.Boolean
     **/
    public boolean updateUserInfo(UserVo userVo) {
        List<String> roles = this.getRolesByCode(this.getCodeByToken());
        UserVo user = this.getUserInfo();
        for (String role : roles) {
            if (role.equals(Const.TEACHER)) {
                Teacher teacher = new Teacher();
                BeanUtils.copyProperties(userVo, teacher);
                teacher.setId(user.getId());
                return this.teacherService.updateById(teacher);
            }
        }
        return false;
    }


    public  ServerResponse uploadAvatar(MultipartFile image) throws IOException {
        String url="";
        //检查图片的大小
        boolean flag = FileUtil.checkFileSize(image.getSize(), 5, "M");


        if (!flag)
        {
            //图片大小超限
            return ServerResponse.createByErrorMessage("图片大小超过限制(5M), 请重新上传");
        }
        if (!image.isEmpty())
        {
            String originalFilename = image.getOriginalFilename();//获取图片文件的名字
            String path = null;
            String type = null; //图片类型
            type = originalFilename.indexOf(".") != -1 ?
                    originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length())
                    : null;

            if (type != null)
            {
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase()))
                {
                    // 新的图片的名称
                    String trueFileName = System.currentTimeMillis() + FileUtil.getRandomString(15)+'.'+type;
                    // 设置存放图片文件的路径
                    path = uploadPicturePath + trueFileName;
                    File file1 = new File(uploadPicturePath);
                    if (!file1.exists())
                    {
                        file1.mkdirs();
                    }
                    //把图片存储到服务器中
                    image.transferTo(new File(path));
                    url = trueFileName;
                    return ServerResponse.createBySuccessMessage("上传成功!",url);
                }
                return ServerResponse.createByErrorMessage("上传图片失败, 文件类型错误");
            }
        }
        return ServerResponse.createByErrorMessage("上传图片失败, 请重新上传");
    }

}
