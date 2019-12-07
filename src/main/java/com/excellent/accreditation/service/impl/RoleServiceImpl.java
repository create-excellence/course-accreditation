package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.exception.AuthenticationException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.dao.RoleMapper;
import com.excellent.accreditation.model.entity.Role;
import com.excellent.accreditation.model.entity.Student;
import com.excellent.accreditation.model.entity.Teacher;
import com.excellent.accreditation.model.form.RoleQuery;
import com.excellent.accreditation.service.IRoleService;
import com.excellent.accreditation.service.IStudentService;
import com.excellent.accreditation.service.ITeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author evildoer
 * @since 2019-12-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private final IStudentService studentService;

    private final ITeacherService teacherService;

    public RoleServiceImpl(IStudentService studentService, ITeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    /**
     * @Author 安羽兮
     * @Description 检测角色名称是否正确
     * @Date 14:58 2019/12/7
     * @Param [role]
     * @Return void
     **/
    public void checkRole(String role) {
        String[] roles = {Const.STUDENT, Const.TEACHER, Const.ADMIN};
        for (String r : roles) {
            if (r.equals(role))
                return;
        }
        throw new AuthenticationException("用户角色分配异常！");
    }

    /**
     * @Author 安羽兮
     * @Description 检测是否有编号为code的用户存在, 返回期待role
     * @Date 14:59 2019/12/7
     * @Param [code]
     * @Return String
     **/
    public String checkCode(String code) {
        Student student = studentService.getByCode(code);
        Teacher teacher = teacherService.getByCode(code);
        // 只要存在一个用户即可
        if (student != null)
            return Const.STUDENT;
        if (teacher != null)
            return Const.TEACHER;
        throw new AuthenticationException("用户角色分配异常！");
    }

    @Override
    public boolean create(Role role) {
        // 外键约束
        String expect = checkCode(role.getCode());
        checkRole(role.getRole());
        // 防止给学生分配老师角色
        if (!expect.equals(role.getRole()) && !Const.ADMIN.equals(role.getRole())) {
            throw new AuthenticationException("用户角色分配异常！");
        }

        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        boolean result = this.save(role);
        // 操作成功
        if (result)
            return true;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }

    @Override
    public PageInfo<Role> pageByQuery(RoleQuery roleQuery) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(roleQuery.getCode()))
            queryWrapper.like(Role::getCode, roleQuery.getCode());
        if (StringUtils.isNotEmpty(roleQuery.getRole()))
            queryWrapper.like(Role::getRole, roleQuery.getRole());
        PageHelper.startPage(roleQuery.getPage(), roleQuery.getPageSize());
        List<Role> list = this.list(queryWrapper);
        return new PageInfo<>(list);
    }
}
