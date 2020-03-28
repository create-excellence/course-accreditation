package com.excellent.accreditation.controller;


import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.manage.UserManage;
import com.excellent.accreditation.model.entity.CourseClass;
import com.excellent.accreditation.model.form.CourseClassQuery;
import com.excellent.accreditation.model.vo.CourseClassVo;
import com.excellent.accreditation.service.ICourseClassService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/${server.version}/course-class")
public class CourseClassController {

    private final UserManage userManage;

    private final ICourseClassService courseClassService;

    public CourseClassController(UserManage userManage,
                                 ICourseClassService courseClassService) {
        this.userManage = userManage;
        this.courseClassService = courseClassService;
    }

    /**
     * @Description: 添加对应课程的开课班级
     * @Param: [course]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: ashe
     * @Date: 2019/12/6
     */
    @PostMapping
    @ApiOperation("添加对应课程的开课班级")
    @Permission
    public ServerResponse create(@RequestBody @NonNull CourseClass courseClass) {
        courseClassService.create(courseClass);
        return ServerResponse.createBySuccessMessage("开课班级添加成功");
    }

    /**
     * @Description: 通过id删除对应课程的开课班级
     * @Param: [id]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: ashe
     * @Date: 2019/12/6
     */

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("通过id删除对应课程的开课班级")
    @Permission
    public ServerResponse deleteById(@PathVariable("id") Integer id) {
        boolean result = courseClassService.removeById(id);
        if (result)
            return ServerResponse.createBySuccessMessage("课程删除成功");

        return ServerResponse.createByErrorMessage("课程删除失败");
    }

    /**
     * @Description: 通过id列表批量删除开课班级
     * @Param: [ids]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: ashe
     * @Date: 2019/12/6
     */
    @DeleteMapping("/deleteByIds")
    @ApiOperation("通过id列表批量删除开课班级")
    @Permission
    public ServerResponse deleteByIds(@RequestBody @NonNull Collection<Integer> ids) {
        boolean result = courseClassService.removeByIds(ids);
        if (result)
            return ServerResponse.createBySuccessMessage("课程批量删除成功");

        return ServerResponse.createByErrorMessage("课程批量删除失败");
    }

    /**
     * @Description: 通过id更新开课班级
     * @Param: [id, courseClass]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: ashe
     * @Date: 2019/12/6
     */
    @PutMapping("/{id:\\d+}")
    @ApiOperation("通过id更新开课班级")
    @Permission
    public ServerResponse updateById(@PathVariable("id") Integer id,
                                     @RequestBody CourseClass courseClass) {
        courseClass.setId(id);
        courseClass.setUpdateTime(LocalDateTime.now());
        courseClassService.check(courseClass, Const.UPDATE);
        boolean result = courseClassService.updateById(courseClass);
        if (result)
            return ServerResponse.createBySuccessMessage("课程更新成功");

        return ServerResponse.createByErrorMessage("课程更新失败");
    }

    /**
     * @Description: 通过id查找开课班级
     * @Param: [id]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse<com.excellent.accreditation.model.entity.CourseClass>
     * @Author: ashe
     * @Date: 2019/12/6
     */
    @GetMapping
    @ApiOperation("通过id查找开课班级")
    @Permission
    public ServerResponse<CourseClass> query(Integer id) {
        CourseClassVo courseClassVo = courseClassService.queryCourseClassById(id);
        if (courseClassVo != null)
            return ServerResponse.createBySuccess(courseClassVo);
        return ServerResponse.createByErrorMessage("课程不存在");
    }


    /**
     * @Description: 分页查询课程对应的开课班级
     * @Param: [courseClassQuery]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: ashe
     * @Date: 2019/12/6
     */
    @GetMapping("/list")
    @ApiOperation("分页查询课程对应的开课班级")
    @Permission
    public ServerResponse queryCourse(CourseClassQuery courseClassQuery) {
        PageInfo<CourseClassVo> list = courseClassService.pageByQuery(courseClassQuery);
        return ServerResponse.createBySuccess(list);

    }

    /**
     * @Description: 分页查询课程对应的开课班级
     * @Param: [courseClassQuery]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: ashe
     * @Date: 2019/12/6
     */
    @GetMapping("/getMyCourse")
    @ApiOperation("获取用户的所有开课班级")
    @Permission
    public ServerResponse getMyCourse(CourseClassQuery query) {
        PageInfo<CourseClassVo> list = courseClassService.getMyCourse(query);
        return ServerResponse.createBySuccess(list);
    }
}
