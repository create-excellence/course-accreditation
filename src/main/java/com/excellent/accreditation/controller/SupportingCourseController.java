package com.excellent.accreditation.controller;


import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.model.entity.SupportingCourse;
import com.excellent.accreditation.model.form.SupportingCourseQuery;
import com.excellent.accreditation.model.vo.SupportingCourseVo;
import com.excellent.accreditation.service.ISupportingCourseService;
import com.excellent.accreditation.service.ISupportingCourseService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 毕业要求指标点支撑课程
 * </p>
 *
 * @author ashe
 * @since 2019-11-14
 */
@RestController
@RequestMapping("/${server.version}/supporting-course")
public class SupportingCourseController {

    private final ISupportingCourseService supportingCourseService;

    @Autowired
    public SupportingCourseController(ISupportingCourseService supportingCourseService) {
        this.supportingCourseService = supportingCourseService;
    }
    /**
     *@Description: 添加毕业要求指标点支撑课程
     *@Param: [course]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse
     *@Author: ashe
     *@Date: 2019/12/6
     */
    @PostMapping
    @ApiOperation("添加毕业要求指标点支撑课程")
    @Permission
    public ServerResponse create(@RequestBody @NonNull SupportingCourse supportingCourse) {
        supportingCourseService.create(supportingCourse);
        return ServerResponse.createBySuccessMessage("支撑课程加成功");
    }

    /**
     *@Description: 通过id删除对应毕业要求指标点支撑课程
     *@Param: [id]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse
     *@Author: ashe
     *@Date: 2019/12/6
     */

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("通过id删除对应毕业要求指标点支撑课程")
    @Permission
    public ServerResponse deleteById(@PathVariable("id") Integer id) {
        boolean result = supportingCourseService.removeById(id);
        if (result)
            return ServerResponse.createBySuccessMessage("支撑课程删除成功");

        return ServerResponse.createByErrorMessage("支撑课程删除失败");
    }

    /**
     *@Description: 通过id列表批量删除对应毕业要求指标点支撑课程
     *@Param: [ids]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse
     *@Author: ashe
     *@Date: 2019/12/6
     */
    @DeleteMapping("/deleteByIds")
    @ApiOperation("通过id列表批量删除开课班级")
    @Permission
    public ServerResponse deleteByIds(@RequestBody @NonNull Collection<Integer> ids) {
        boolean result = supportingCourseService.removeByIds(ids);
        if (result)
            return ServerResponse.createBySuccessMessage("支撑课程批量删除成功");

        return ServerResponse.createByErrorMessage("支撑课程批量删除失败");
    }

    /**
     *@Description: 通过id更新毕业要求指标点支撑课程
     *@Param: [id, supportingCourse]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse
     *@Author: ashe
     *@Date: 2019/12/6
     */
    @PutMapping("/{id:\\d+}")
    @ApiOperation("通过id更新毕业要求指标点支撑课程")
    @Permission
    public ServerResponse updateById(@PathVariable("id") Integer id,
                                     @RequestBody SupportingCourse supportingCourse) {
        supportingCourse.setId(id);
        supportingCourse.setUpdateTime(LocalDateTime.now());
        supportingCourseService.check(supportingCourse, Const.UPDATE);
        boolean result = supportingCourseService.updateById(supportingCourse);
        if (result)
            return ServerResponse.createBySuccessMessage("支撑课程更新成功");

        return ServerResponse.createByErrorMessage("支撑课程更新失败");
    }

    /**
     *@Description: 通过id查找毕业要求指标点支撑课程
     *@Param: [id]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse<com.excellent.accreditation.model.entity.SupportingCourse>
     *@Author: ashe
     *@Date: 2019/12/6
     */
    @GetMapping
    @ApiOperation("通过id查找毕业要求指标点支撑课程")
    @Permission
    public ServerResponse<SupportingCourseVo> query(Integer id) {
        SupportingCourseVo supportingCourse = supportingCourseService.getVoById(id);
        if (supportingCourse != null)
            return ServerResponse.createBySuccess(supportingCourse);
        return ServerResponse.createByErrorMessage("毕业要求指标点支撑课程不存在");
    }


    /**
     *@Description: 分页查询毕业要求指标点支撑课程
     *@Param: [supportingCourseQuery]
     *@Return: com.excellent.accreditation.common.domain.ServerResponse
     *@Author: ashe
     *@Date: 2019/12/6
     */
    @GetMapping("/list")
    @ApiOperation("分页查询毕业要求指标点支撑课程")
    @Permission
    public ServerResponse queryCourse(SupportingCourseQuery supportingCourseQuery) {
        PageInfo<SupportingCourseVo> list = supportingCourseService.pageByQuery(supportingCourseQuery);
        if (list != null)
            return ServerResponse.createBySuccess(list);

        return ServerResponse.createByErrorMessage("支撑课程不存在");
    }

    /**
     * @Description: 通过excel批量添加支撑课程
     * @Param: [file]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: ashe
     * @Date: 2019/12/5
     */
    @PostMapping("/batchSave")
    @ApiOperation("批量添加支撑课程")
    @Permission
    public ServerResponse batchSave(MultipartFile file) {
        return ServerResponse.createBySuccess(supportingCourseService.saveBachByExcel(file));
    }

}
