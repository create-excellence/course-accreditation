package com.excellent.accreditation.controller;


import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.model.entity.GraduationPoint;
import com.excellent.accreditation.model.form.GraduationPointQuery;
import com.excellent.accreditation.service.IGraduationPointService;
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
@RequestMapping("/${server.version}/graduation-point")
public class GraduationPointController {


    private final IGraduationPointService graduationPointService;

    @Autowired
    public GraduationPointController(IGraduationPointService graduationPointService) {
        this.graduationPointService = graduationPointService;
    }

    /**
     * @Author 安羽兮
     * @Description 添加指标点
     * @Date 10:19 2019/11/15
     * @Param [graduationPoint]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PostMapping
    @ApiOperation("添加指标点")
    @Permission
    public ServerResponse create(@RequestBody @NonNull GraduationPoint graduationPoint) {
        graduationPointService.create(graduationPoint);
        return ServerResponse.createBySuccess("指标点添加成功");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id删除指标点
     * @Date 10:18 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("通过id删除指标点")
    @Permission
    public ServerResponse deleteById(@PathVariable("id") Integer id) {
        boolean result = graduationPointService.removeById(id);
        if (result)
            return ServerResponse.createBySuccess("指标点删除成功");

        return ServerResponse.createByErrorMessage("指标点删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id列表批量删除指标点
     * @Date 10:22 2019/11/15
     * @Param [ids]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/deleteByIds")
    @ApiOperation("通过id列表批量删除指标点")
    @Permission
    public ServerResponse deleteByIds(@NonNull Collection<Integer> ids) {
        boolean result = graduationPointService.removeByIds(ids);
        if (result)
            return ServerResponse.createBySuccess("指标点批量删除成功");

        return ServerResponse.createByErrorMessage("指标点批量删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id更新指标点
     * @Date 10:49 2019/11/15
     * @Param [id, graduationPoint]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PutMapping("/{id:\\d+}")
    @ApiOperation("通过id更新指标点")
    @Permission
    public ServerResponse updateById(@PathVariable("id") Integer id,
                                     @RequestBody GraduationPoint graduationPoint) {
        graduationPoint.setId(id);
        graduationPoint.setUpdateTime(LocalDateTime.now());
        boolean result = graduationPointService.updateById(graduationPoint);
        if (result)
            return ServerResponse.createBySuccess("指标点更新成功");

        return ServerResponse.createByErrorMessage("指标点更新失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id查找指标点
     * @Date 10:23 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping
    @ApiOperation("通过id查找指标点")
    @Permission
    public ServerResponse<GraduationPoint> query(Integer id) {
        GraduationPoint graduationPoint = graduationPointService.getById(id);
        if (graduationPoint != null)
            return ServerResponse.createBySuccess(graduationPoint);

        return ServerResponse.createByErrorMessage("指标点不存在");
    }

    /**
     * @Author 安羽兮
     * @Description 分页查询指标点
     * @Date 16:52 2019/12/3
     * @Param [graduationPointQuery]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping("/list")
    @ApiOperation("分页查询指标点")
    @Permission
    public ServerResponse queryGraduationPoint(GraduationPointQuery graduationPointQuery) {
        PageInfo<GraduationPoint> list = graduationPointService.pageByQuery(graduationPointQuery);
        if (list != null)
            return ServerResponse.createBySuccess(list);

        return ServerResponse.createByErrorMessage("指标点不存在");
    }
}
