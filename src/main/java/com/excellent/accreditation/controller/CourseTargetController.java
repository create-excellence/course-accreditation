package com.excellent.accreditation.controller;


import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.model.entity.CourseTarget;
import com.excellent.accreditation.model.form.CourseTargetQuery;
import com.excellent.accreditation.model.vo.CourseTargetVo;
import com.excellent.accreditation.service.ICourseTargetService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ashe
 * @since 2019-11-14
 */
@RestController
@RequestMapping("/${server.version}/course-target")
public class CourseTargetController {

    private final ICourseTargetService courseTargetService;

    @Autowired
    public CourseTargetController(ICourseTargetService courseTargetService) {
        this.courseTargetService = courseTargetService;
    }

    /**
     *@Description: 添加课程目标
     *@Param: [course]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse
     *@Author: ashe
     *@Date: 2019/12/6
     */
    @PostMapping
    @ApiOperation("添加课程目标")
    @Permission
    public ServerResponse create(@RequestBody @NonNull CourseTarget courseClass) {
        courseTargetService.create(courseClass);
        return ServerResponse.createBySuccess("课程目标添加成功");
    }

    /**
     *@Description: 通过id删除课程目标
     *@Param: [id]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse
     *@Author: ashe
     *@Date: 2019/12/6
     */

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("通过id删除课程目标")
    @Permission
    public ServerResponse deleteById(@PathVariable("id") Integer id) {
        boolean result = courseTargetService.removeById(id);
        if (result)
            return ServerResponse.createBySuccess("课程目标删除成功");

        return ServerResponse.createByErrorMessage("课程目标删除失败");
    }

    /**
     *@Description: 通过id列表批量删除课程目标
     *@Param: [ids]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse
     *@Author: ashe
     *@Date: 2019/12/6
     */
    @DeleteMapping("/deleteByIds")
    @ApiOperation("通过id列表批量删除课程目标")
    @Permission
    public ServerResponse deleteByIds(@RequestBody @NonNull Collection<Integer> ids) {
        boolean result = courseTargetService.removeByIds(ids);
        if (result)
            return ServerResponse.createBySuccess("课程目标批量删除成功");

        return ServerResponse.createByErrorMessage("课程目标批量删除失败");
    }

    /**
     *@Description: 通过id更新课程目标
     *@Param: [id, courseClass]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse
     *@Author: ashe
     *@Date: 2019/12/6
     */
    @PutMapping("/{id:\\d+}")
    @ApiOperation("通过id更新课程目标")
    @Permission
    public ServerResponse updateById(@PathVariable("id") Integer id,
                                     @RequestBody CourseTarget courseClass) {
        courseClass.setId(id);
        courseClass.setUpdateTime(LocalDateTime.now());
        courseTargetService.check(courseClass, Const.UPDATE);
        boolean result = courseTargetService.updateById(courseClass);
        if (result)
            return ServerResponse.createBySuccess("课程目标更新成功");

        return ServerResponse.createByErrorMessage("课程目标更新失败");
    }

    /**
     *@Description: 通过id查找课程目标
     *@Param: [id]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse<com.excellent.accreditation.model.entity.CourseTarget>
     *@Author: ashe
     *@Date: 2019/12/6
     */
    @GetMapping
    @ApiOperation("通过id查找课程目标")
    @Permission
    public ServerResponse<CourseTarget> query(Integer id) {
        CourseTarget courseClass = courseTargetService.getById(id);
        if (courseClass != null)
            return ServerResponse.createBySuccess(courseClass);
        return ServerResponse.createByErrorMessage("课程目标不存在");
    }


    /**
     *@Description: 分页查询课程目标
     *@Param: [courseClassQuery]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse
     *@Author: ashe
     *@Date: 2019/12/6
     */
    @GetMapping("/list")
    @ApiOperation("分页查询课程目标")
    @Permission
    public ServerResponse queryCourse(CourseTargetQuery courseClassQuery) {
        PageInfo<CourseTargetVo> list = courseTargetService.pageByQuery(courseClassQuery);
        if (list != null)
            return ServerResponse.createBySuccess(list);
        return ServerResponse.createByErrorMessage("课程目标不存在");
    }


}
