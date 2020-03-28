package com.excellent.accreditation.controller;


import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.model.entity.Course;
import com.excellent.accreditation.model.form.CourseQuery;
import com.excellent.accreditation.service.ICourseService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@Slf4j
@RestController
@RequestMapping("/${server.version}/course")
public class CourseController {

    private final ICourseService courseService;

    @Autowired
    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * @Author 安羽兮
     * @Description 添加课程
     * @Date 10:19 2019/11/15
     * @Param [course]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PostMapping
    @ApiOperation("添加课程")
    @Permission
    public ServerResponse create(@RequestBody @NonNull Course course) {
        courseService.create(course);
        return ServerResponse.createBySuccessMessage("课程添加成功");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id删除课程
     * @Date 10:18 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("通过id删除课程")
    @Permission
    public ServerResponse deleteById(@PathVariable("id") Integer id) {
        boolean result = courseService.removeById(id);
        if (result)
            return ServerResponse.createBySuccessMessage("课程删除成功");

        return ServerResponse.createByErrorMessage("课程删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id列表批量删除课程
     * @Date 10:22 2019/11/15
     * @Param [ids]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/deleteByIds")
    @ApiOperation("通过id列表批量删除课程")
    @Permission
    public ServerResponse deleteByIds(@RequestBody @NonNull Collection<Integer> ids) {
        boolean result = courseService.removeByIds(ids);
        if (result)
            return ServerResponse.createBySuccessMessage("课程批量删除成功");

        return ServerResponse.createByErrorMessage("课程批量删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id更新课程
     * @Date 10:49 2019/11/15
     * @Param [id, course]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PutMapping("/{id:\\d+}")
    @ApiOperation("通过id更新课程")
    @Permission
    public ServerResponse updateById(@PathVariable("id") Integer id,
                                     @RequestBody Course course) {
        course.setId(id);
        course.setUpdateTime(LocalDateTime.now());
        courseService.checkCode(course.getCode(), course.getId());
        boolean result = courseService.updateById(course);
        if (result)
            return ServerResponse.createBySuccessMessage("课程更新成功");

        return ServerResponse.createByErrorMessage("课程更新失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id查找课程
     * @Date 10:23 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping
    @ApiOperation("通过id查找课程")
    @Permission
    public ServerResponse<Course> query(Integer id) {
        Course course = courseService.getById(id);
        if (course != null)
            return ServerResponse.createBySuccess(course);

        return ServerResponse.createByErrorMessage("课程不存在");
    }

    /**
     * @Author 安羽兮
     * @Description 分页查询课程
     * @Date 16:52 2019/12/3
     * @Param [courseQuery]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping("/list")
    @ApiOperation("分页查询课程")
    @Permission
    public ServerResponse queryCourse(CourseQuery courseQuery) {
        PageInfo<Course> list = courseService.pageByQuery(courseQuery);
        if (list != null)
            return ServerResponse.createBySuccess(list);

        return ServerResponse.createByErrorMessage("课程不存在");
    }


    @PostMapping("/batchSave")
    @ApiOperation("批量添加课程")
    @Permission
    public ServerResponse batchSave(MultipartFile file) {
        return ServerResponse.createBySuccess(courseService.saveBachByExcel(file));
    }
}
