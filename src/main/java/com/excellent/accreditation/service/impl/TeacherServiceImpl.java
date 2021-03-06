package com.excellent.accreditation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.CommonException;
import com.excellent.accreditation.common.exception.ConflictException;
import com.excellent.accreditation.common.exception.DatabaseException;
import com.excellent.accreditation.common.exception.UniqueException;
import com.excellent.accreditation.dao.TeacherMapper;
import com.excellent.accreditation.model.entity.Course;
import com.excellent.accreditation.model.entity.Role;
import com.excellent.accreditation.model.entity.Teacher;
import com.excellent.accreditation.model.form.TeacherQuery;
import com.excellent.accreditation.service.IRoleService;
import com.excellent.accreditation.service.ITeacherService;
import com.excellent.accreditation.untils.EmptyCheckUtils;
import com.excellent.accreditation.untils.ExcelUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author evildoer
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

   private final IRoleService roleService;
    @Autowired
   public TeacherServiceImpl(IRoleService roleService){
        this.roleService=roleService;
    }


    @Override
    public Teacher getByCode(String code) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teacher::getJno, code);       // 通过工号查询
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public void checkJno(String jno) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teacher::getJno, jno);
        if (super.getOne(queryWrapper) != null) {
            throw new UniqueException("工号不能重复");
        }
    }

    @Override
    public PageInfo<Teacher> pageByQuery(TeacherQuery query) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(query.getJno()))
            queryWrapper.like(Teacher::getJno, query.getJno());
        if (StringUtils.isNotEmpty(query.getTitle()))
            queryWrapper.like(Teacher::getTitle, query.getTitle());
        if (StringUtils.isNotEmpty(query.getName()))
            queryWrapper.like(Teacher::getName, query.getName());
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<Teacher> list = this.list(queryWrapper);
        PageInfo<Teacher> pageInfo = new PageInfo<>(list);
        List<Teacher> teachers = new ArrayList<>();
        pageInfo.getList().forEach(teacher -> {
            // 隐藏密码
            teachers.add(teacher.setPassword(Const.PASSWORD));
        });
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public List<ExcelResult> saveBachByExcel(MultipartFile file) {
        List<Map<Integer, String>> list = ExcelUtils.readExcelGetList(file);
        List<ExcelResult> excelResults = new ArrayList<>();
        list.forEach(data -> {
            ExcelResult excelResult = new ExcelResult();
            try {
                EmptyCheckUtils.checkExcelMapAndSetNo(data, excelResult, 4);
                String jno = data.get(1);
                String name = data.get(2);
                String password = data.get(3);
                String sex = data.get(4);
                Teacher teacher =new Teacher();
                teacher.setJno(jno);
                teacher.setName(name);
                teacher.setSex(sex);
                teacher.setPassword(password);
                if (this.create(teacher)) {
                    excelResult.setStatus(Const.SUCCESS_INCREASE);
                    excelResult.setMessage("添加成功");
                }
            } catch (NumberFormatException e) {
                excelResult.setMessage("无法将部分字段转为数字类型");
            } catch (CommonException e) {
                excelResult.setMessage(e.getMessage());
            }
            excelResults.add(excelResult);
        });
        return excelResults;
    }

    @Override
    public boolean create(Teacher teacher) {
        teacher.setCreateTime(LocalDateTime.now());
        teacher.setUpdateTime(LocalDateTime.now());
        this.checkJno(teacher.getJno());            // 工号必须唯一
        boolean result = this.save(teacher);
        Role role = new Role();
        role.setCode(teacher.getJno());
        role.setRole(Const.TEACHER);
        roleService.create(role);
        // 操作成功
        if (result)
            return true;
        throw new DatabaseException("未知异常, 数据库操作失败");
    }

    @Override
    public void checkTeacher(Integer teacherId) {
        if (this.getById(teacherId) == null) {
            throw new ConflictException("老师不存在");
        }
    }

    @Override
    public boolean updatePassword(String code, String oldPassword, String newPassword) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teacher::getJno, code)
                .eq(Teacher::getPassword, oldPassword);
        Teacher teacher = this.baseMapper.selectOne(queryWrapper);
        if (teacher != null) {
            teacher.setPassword(newPassword);
            LambdaUpdateWrapper<Teacher> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Teacher::getJno, code);
            this.baseMapper.update(teacher, updateWrapper);
            return true;
        }
        throw new ConflictException("密码错误，修改失败");
    }
}
