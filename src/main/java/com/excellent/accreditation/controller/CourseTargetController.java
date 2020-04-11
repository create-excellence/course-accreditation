package com.excellent.accreditation.controller;


import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.model.entity.CourseTarget;
import com.excellent.accreditation.model.entity.GraduationPoint;
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
import java.util.List;

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
        return ServerResponse.createBySuccessMessage("课程目标添加成功");
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
        boolean result = courseTargetService.delete(id);
        if (result)
            return ServerResponse.createBySuccessMessage("课程目标删除成功");

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
            return ServerResponse.createBySuccessMessage("课程目标批量删除成功");

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
            return ServerResponse.createBySuccessMessage("课程目标更新成功");

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
     *@Param: [courseTargetQuery]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse
     *@Author: ashe
     *@Date: 2019/12/6
     */
    @GetMapping("/list")
    @ApiOperation("分页查询课程目标")
    @Permission
    public ServerResponse queryCourse(CourseTargetQuery courseTargetQuery) {
        PageInfo<CourseTargetVo> list = courseTargetService.pageByQuery(courseTargetQuery);
        return ServerResponse.createBySuccess(list);
    }

    @GetMapping("/point")
    @ApiOperation("查询对应指标点")
    @Permission
    public ServerResponse  point (Integer questionnaireId){
        List<GraduationPoint> list = courseTargetService.point(questionnaireId);
        return ServerResponse.createBySuccess(list);
    }


    /**
     *@Description: 问卷题目上移或下移
     *@Param: [courseTargetId,operate]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse
     *@Author: ashe
     *@Date: 2019/12/6
     */
    @GetMapping("/move")
    @ApiOperation("问卷题目上移或下移")
    @Permission
    public ServerResponse move(Integer courseTargetId,Integer operate) {
        if(courseTargetService.moveQuestion(courseTargetId,operate)){
            return ServerResponse.createBySuccess();
        }
         return ServerResponse.createByErrorMessage("操作失败");
    }


    /**
     *@Description: 问卷题目复制
     *@Param: [courseTargetId,operate]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse
     *@Author: ashe
     *@Date: 2019/12/6
     */
    @GetMapping("/copy")
    @ApiOperation("问卷题目复制")
    @Permission
    public ServerResponse copy(Integer courseTargetId) {
        if(courseTargetService.copyQuestion(courseTargetId)){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorMessage("操作失败");
    }


}
