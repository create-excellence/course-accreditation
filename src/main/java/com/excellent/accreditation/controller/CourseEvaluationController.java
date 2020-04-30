package com.excellent.accreditation.controller;


import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.domain.ServerResponse;

import com.excellent.accreditation.model.entity.CourseEvaluation;
import com.excellent.accreditation.model.form.CourseEvaluationQuery;
import com.excellent.accreditation.model.form.CourseEvaluationStudentQuery;
import com.excellent.accreditation.model.vo.CourseEvaluationStudentVo;

import com.excellent.accreditation.model.vo.CourseEvaluationVo;
import com.excellent.accreditation.service.ICourseEvaluationService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author evildoer
 * @since 2020-03-28
 */
@RestController
@RequestMapping("/${server.version}/course-evaluation")
public class CourseEvaluationController {
    private final ICourseEvaluationService courseEvaluationService;

    @Autowired
    public CourseEvaluationController(ICourseEvaluationService courseEvaluationService) {
        this.courseEvaluationService = courseEvaluationService;
    }

    /**
     * @Description: 添加课程评价
     * @Param: [course]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: ashe
     * @Date: 2019/12/6
     */
    @PostMapping
    @ApiOperation("添加课程评价")
    @Permission
    public ServerResponse create(@RequestBody @NonNull CourseEvaluation courseEvaluation) {
        courseEvaluationService.create(courseEvaluation);
        return ServerResponse.createBySuccessMessage("发布成功");
    }

    /**
     * @Description: 通过id删除课程评价
     * @Param: [id]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: ashe
     * @Date: 2019/12/6
     */

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("通过id删除课程评价")
    @Permission
    public ServerResponse deleteById(@PathVariable("id") Integer id) {
        boolean result = courseEvaluationService.removeById(id);
        if (result)
            return ServerResponse.createBySuccessMessage("课程评价删除成功");

        return ServerResponse.createByErrorMessage("课程评价删除失败");
    }

    /**
     * @Description: 通过id查找课程评价
     * @Param: [id]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse<com.excellent.accreditation.model.entity.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               CourseEvaluation>
     * @Author: ashe
     * @Date: 2019/12/6
     */
    @GetMapping
    @ApiOperation("通过id查找课程评价")
    @Permission
    public ServerResponse<CourseEvaluation> query(Integer id) {
        CourseEvaluation courseEvaluation = courseEvaluationService.getById(id);
        if (courseEvaluation != null)
            return ServerResponse.createBySuccess(courseEvaluation);
        return ServerResponse.createByErrorMessage("课程评价不存在");
    }


    /**
     * @Description: 分页查询课程评价
     * @Param: [ courseEvaluationQuery]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: ashe
     * @Date: 2019/12/6
     */
    @GetMapping("/list")
    @ApiOperation("分页查询课程评价")
//    @Permission
    public ServerResponse queryCourse(CourseEvaluationQuery courseEvaluationQuery) {
        PageInfo<CourseEvaluationVo> list = courseEvaluationService.pageByQuery(courseEvaluationQuery);
        return ServerResponse.createBySuccess(list);
    }

    /**
     * @Description: 获取用户参与的所有课程评价
     * @Param: [ courseEvaluationQuery]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: ashe
     * @Date: 2019/12/6
     */
    @GetMapping("/getMyCourseEvaluation")
    @ApiOperation("获取用户参与的所有课程评价")
    @Permission
    public ServerResponse getMyCourseEvaluation(CourseEvaluationQuery courseEvaluationQuery) {
        PageInfo<CourseEvaluationVo> list = courseEvaluationService.getMyCourseEvaluation(courseEvaluationQuery);
        return ServerResponse.createBySuccess(list);
    }

    /**
     *@Description: 获取已参与课程评价的所有学生
     *@Param: [ courseEvaluationQuery]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse
     *@Author: ashe
     *@Date: 2019/12/6
     */
    @GetMapping("/getCourseEvaluationStudent")
    @ApiOperation("获取已参与课程评价的所有学生")
    @Permission
    public ServerResponse getMyCourseEvaluation(CourseEvaluationStudentQuery courseEvaluationStudentQuery) {
        PageInfo<CourseEvaluationStudentVo> list = courseEvaluationService.getCourseEvaluationStudent(courseEvaluationStudentQuery);
        return ServerResponse.createBySuccess(list);
    }

    /**
     *@Description: 开始进行课程评价
     *@Param: [ courseEvaluationQuery]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse
     *@Author: ashe
     *@Date: 2019/12/6
     */
    @GetMapping("/startEvaluation")
    @ApiOperation("开始进行课程评价")
    @Permission(roles = "student")
    public ServerResponse startEvaluation(Integer courseEvaluationId) {
          courseEvaluationService.startEvaluation(courseEvaluationId);
        return ServerResponse.createBySuccess();
    }

    /**
     *@Description: 获得问卷问题
     *@Param: [ courseEvaluationQuery]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse
     *@Author: ashe
     *@Date: 2019/12/6
     */
    @GetMapping("/getQuestions")
    @ApiOperation("获得问卷问题")
    @Permission(roles = "student")
    public ServerResponse getQuestions(Integer courseEvaluationId) {
        courseEvaluationService.getQuestions(courseEvaluationId);
        return ServerResponse.createBySuccess();
    }
}
