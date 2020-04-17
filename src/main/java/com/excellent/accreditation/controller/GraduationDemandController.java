package com.excellent.accreditation.controller;


import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.model.entity.GraduationDemand;
import com.excellent.accreditation.model.form.GraduationDemandQuery;
import com.excellent.accreditation.model.vo.GraduationDemandVo;
import com.excellent.accreditation.service.IGraduationDemandService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
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
@RestController
@RequestMapping("/${server.version}/graduation-demand")
public class GraduationDemandController {


    private final IGraduationDemandService graduationDemandService;

    @Autowired
    public GraduationDemandController(IGraduationDemandService graduationDemandService) {
        this.graduationDemandService = graduationDemandService;
    }

    /**
     * @Author 安羽兮
     * @Description 添加毕业要求
     * @Date 10:19 2019/11/15
     * @Param [graduationDemand]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PostMapping
    @ApiOperation("添加毕业要求")
    @Permission
    public ServerResponse create(@RequestBody @NonNull GraduationDemand graduationDemand) {
        graduationDemandService.create(graduationDemand);
        return ServerResponse.createBySuccessMessage("毕业要求添加成功");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id删除毕业要求
     * @Date 10:18 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("通过id删除毕业要求")
    @Permission
    public ServerResponse deleteById(@PathVariable("id") Integer id) {
        boolean result = graduationDemandService.removeById(id);
        if (result)
            return ServerResponse.createBySuccessMessage("毕业要求删除成功");
        return ServerResponse.createByErrorMessage("毕业要求删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id列表批量删除毕业要求
     * @Date 10:22 2019/11/15
     * @Param [ids]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/deleteByIds")
    @ApiOperation("通过id列表批量删除毕业要求")
    @Permission
    public ServerResponse deleteByIds(@RequestBody @NonNull Collection<Integer> ids) {
        boolean result = graduationDemandService.removeByIds(ids);
        if (result)
            return ServerResponse.createBySuccessMessage("毕业要求批量删除成功");

        return ServerResponse.createByErrorMessage("毕业要求批量删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id更新毕业要求
     * @Date 10:49 2019/11/15
     * @Param [id, graduationDemand]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PutMapping("/{id:\\d+}")
    @ApiOperation("通过id更新毕业要求")
    @Permission
    public ServerResponse updateById(@PathVariable("id") Integer id,
                                     @RequestBody GraduationDemand graduationDemand) {
        graduationDemand.setId(id);
        graduationDemand.setUpdateTime(LocalDateTime.now());
        boolean result = graduationDemandService.updateById(graduationDemand);
        if (result)
            return ServerResponse.createBySuccessMessage("毕业要求更新成功");

        return ServerResponse.createByErrorMessage("毕业要求更新失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id查找毕业要求
     * @Date 10:23 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping
    @ApiOperation("通过id查找毕业要求")
    @Permission
    public ServerResponse<GraduationDemand> query(Integer id) {
        GraduationDemand graduationDemand = graduationDemandService.getById(id);
        if (graduationDemand != null)
            return ServerResponse.createBySuccess(graduationDemand);

        return ServerResponse.createByErrorMessage("毕业要求不存在");
    }

    /**
     * @Author 安羽兮
     * @Description 分页查询毕业要求
     * @Date 14:18 2019/12/5
     * @Param [graduationDemandQuery]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping("/list")
    @ApiOperation("分页查询毕业要求")
    @Permission
    public ServerResponse queryGraduationDemand(GraduationDemandQuery graduationDemandQuery) {
        PageInfo<GraduationDemandVo> list = graduationDemandService.pageByQuery(graduationDemandQuery);
        if (list != null)
            return ServerResponse.createBySuccess(list);

        return ServerResponse.createByErrorMessage("毕业要求不存在");
    }

    @PostMapping("/batchSave")
    @ApiOperation("批量添加毕业要求")
    @Permission
    public ServerResponse batchSave(MultipartFile file) {
        return ServerResponse.createBySuccess(graduationDemandService.saveBachByExcel(file));
    }
}
