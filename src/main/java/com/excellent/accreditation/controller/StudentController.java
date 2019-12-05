package com.excellent.accreditation.controller;


import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.model.entity.Student;
import com.excellent.accreditation.model.form.StudentQuery;
import com.excellent.accreditation.model.vo.StudentVo;
import com.excellent.accreditation.service.IMajorService;
import com.excellent.accreditation.service.IStudentService;
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
@RequestMapping("/${server.version}/student")
public class StudentController {

    private final IStudentService studentService;

    private final IMajorService majorService;

    @Autowired
    public StudentController(IStudentService studentService, IMajorService majorService) {
        this.studentService = studentService;
        this.majorService = majorService;
    }

    /**
     * @Author 安羽兮
     * @Description 添加学生
     * @Date 10:19 2019/11/15
     * @Param [student]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PostMapping
    @ApiOperation("添加学生")
    @Permission
    public ServerResponse create(@RequestBody @NonNull Student student) {
        studentService.create(student);
        return ServerResponse.createBySuccess("学生添加成功");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id删除学生
     * @Date 10:18 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("通过id删除学生")
    @Permission
    public ServerResponse deleteById(@PathVariable("id") Integer id) {
        boolean result = studentService.removeById(id);
        if (result)
            return ServerResponse.createBySuccess("学生删除成功");

        return ServerResponse.createByErrorMessage("学生删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id列表批量删除学生
     * @Date 9:05 2019/12/4
     * @Param [ids]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/deleteByIds")
    @ApiOperation("通过id列表批量删除学生")
    @Permission
    public ServerResponse deleteByIds(@NonNull Collection<Integer> ids) {
        boolean result = studentService.removeByIds(ids);
        if (result)
            return ServerResponse.createBySuccess("学生批量删除成功");

        return ServerResponse.createByErrorMessage("学生批量删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id更新学生
     * @Date 9:05 2019/12/4
     * @Param [id, student]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PutMapping("/{id:\\d+}")
    @ApiOperation("通过id更新学生")
    @Permission
    public ServerResponse updateById(@PathVariable("id") Integer id,
                                     @RequestBody Student student) {
        student.setId(id);
        student.setUpdateTime(LocalDateTime.now());
        boolean result = studentService.updateById(student);
        if (result)
            return ServerResponse.createBySuccess("学生更新成功");

        return ServerResponse.createByErrorMessage("学生更新失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id查找学生
     * @Date 9:05 2019/12/4
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse<com.excellent.accreditation.model.entity.Student>
     **/
    @GetMapping
    @ApiOperation("通过id查找学生")
    @Permission
    public ServerResponse<Student> query(Integer id) {
        Student student = studentService.getById(id);
        if (student != null)
            return ServerResponse.createBySuccess(student);

        return ServerResponse.createByErrorMessage("学生不存在");
    }

    /**
     * @Author 安羽兮
     * @Description 分页查询学生
     * @Date 9:05 2019/12/4
     * @Param [studentQuery]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping("/list")
    @ApiOperation("分页查询学生")
    @Permission
    public ServerResponse queryStudent(StudentQuery studentQuery) {
        PageInfo<StudentVo> list = studentService.pageByQuery(studentQuery);
        if (list != null)
            return ServerResponse.createBySuccess(list);

        return ServerResponse.createByErrorMessage("学生不存在");
    }
}
