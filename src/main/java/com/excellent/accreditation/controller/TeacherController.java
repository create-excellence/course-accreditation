package com.excellent.accreditation.controller;


import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.model.entity.Teacher;
import com.excellent.accreditation.model.form.TeacherQuery;
import com.excellent.accreditation.service.ITeacherService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ashe
 * @since 2019-11-14
 */
@RestController
@RequestMapping("/${server.version}/teacher")
public class   TeacherController {

    private final ITeacherService teacherService;

    @Autowired
    public TeacherController(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }


    /**
     * @MethodName: create
     * @Description: 添加教师
     * @Param: [teacher]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: Nemo
     * @Date: 20:07 2019/12/3
     **/
    @PostMapping
    @ApiOperation("添加教师")
    public ServerResponse create(@RequestBody @NonNull Teacher teacher) {
        teacherService.create(teacher);
        return ServerResponse.createBySuccess("教师添加成功");
    }

    /**
     * @MethodName: deleteById
     * @Description: 通过id删除教师
     * @Param: [id]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: Nemo
     * @Date: 20:07 2019/12/3
     **/
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("通过id删除教师")
    public ServerResponse deleteById(@PathVariable("id") Integer id) {
        boolean result = teacherService.removeById(id);
        if (result)
            return ServerResponse.createBySuccess("教师删除成功");

        return ServerResponse.createByErrorMessage("教师删除失败");
    }

    /**
     * @MethodName: deleteByIds
     * @Description: 通过id列表批量删除教师
     * @Param: [ids]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: Nemo
     * @Date: 20:07 2019/12/3
     **/
    @DeleteMapping("/deleteByIds")
    @ApiOperation("通过id列表批量删除教师")
    public ServerResponse deleteByIds(@RequestBody @NonNull Collection<Integer> ids) {
        boolean result = teacherService.removeByIds(ids);
        if (result)
            return ServerResponse.createBySuccess("教师批量删除成功");

        return ServerResponse.createByErrorMessage("教师批量删除失败");
    }

    /**
     * @MethodName: updateById
     * @Description: 通过id更新教师
     * @Param: [id, teacher]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: Nemo
     * @Date: 20:08 2019/12/3
     **/
    @PutMapping("/{id:\\d+}")
    @ApiOperation("通过id更新教师")
    public ServerResponse updateById(@PathVariable("id") Integer id,
                                     @RequestBody Teacher teacher) {
        teacher.setId(id);
        teacher.setUpdateTime(LocalDateTime.now());
        boolean result = teacherService.updateById(teacher);
        if (result)
            return ServerResponse.createBySuccess("教师更新成功");

        return ServerResponse.createByErrorMessage("教师更新失败");
    }

    /**
     * @MethodName: query
     * @Description: 通过id查找教师
     * @Param: [id]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse<com.excellent.accreditation.model.entity.Teacher>
     * @Author: Nemo
     * @Date: 20:08 2019/12/3
     **/
    @GetMapping
    @ApiOperation("通过id查找教师")
    @Permission
    public ServerResponse<Teacher> query(Integer id) {
        Teacher teacher = teacherService.getById(id);
        if (teacher != null)
            return ServerResponse.createBySuccess(teacher);

        return ServerResponse.createByErrorMessage("教师不存在");
    }

    /**
     * @MethodName: queryTeacher
     * @Description: 分页查询教师
     * @Param: [teacherQuery]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: Nemo
     * @Date: 20:08 2019/12/3
     **/
    @GetMapping("/list")
    @ApiOperation("分页查询教师")
    public ServerResponse queryTeacher(TeacherQuery teacherQuery) {
        PageInfo<Teacher> list = teacherService.pageByQuery(teacherQuery);
        if (list != null)
            return ServerResponse.createBySuccess(list);

        return ServerResponse.createByErrorMessage("教师不存在");
    }
}
