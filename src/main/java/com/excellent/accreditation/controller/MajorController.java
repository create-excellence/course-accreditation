package com.excellent.accreditation.controller;


import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.model.entity.Major;
import com.excellent.accreditation.model.form.MajorQuery;
import com.excellent.accreditation.service.IMajorService;
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
@RequestMapping("/${server.version}/major")
public class MajorController {

    private final IMajorService majorService;

    @Autowired
    public MajorController(IMajorService majorService) {
        this.majorService = majorService;
    }

    /**
     * @Author 安羽兮
     * @Description 添加专业
     * @Date 10:19 2019/11/15
     * @Param [major]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PostMapping
    @ApiOperation("添加专业")
    @Permission
    public ServerResponse create(@RequestBody @NonNull Major major) {
        majorService.create(major);
        return ServerResponse.createBySuccessMessage("专业添加成功");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id删除专业
     * @Date 10:18 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("通过id删除专业")
    @Permission
    public ServerResponse deleteById(@PathVariable("id") Integer id) {
        boolean result = majorService.removeById(id);
        if (result)
            return ServerResponse.createBySuccessMessage("专业删除成功");

        return ServerResponse.createByErrorMessage("专业删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id列表批量删除专业
     * @Date 10:22 2019/11/15
     * @Param [ids]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/deleteByIds")
    @ApiOperation("通过id列表批量删除专业")
    @Permission
    public ServerResponse deleteByIds(@RequestBody @NonNull Collection<Integer> ids) {
        boolean result = majorService.removeByIds(ids);
        if (result)
            return ServerResponse.createBySuccessMessage("专业批量删除成功");

        return ServerResponse.createByErrorMessage("专业批量删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id更新专业
     * @Date 10:49 2019/11/15
     * @Param [id, major]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PutMapping("/{id:\\d+}")
    @ApiOperation("通过id更新专业")
    @Permission
    public ServerResponse updateById(@PathVariable("id") Integer id,
                                     @RequestBody Major major) {
        major.setId(id);
        major.setUpdateTime(LocalDateTime.now());
        majorService.checkCode(major.getCode(),id);
        boolean result = majorService.updateById(major);
        if (result)
            return ServerResponse.createBySuccessMessage("专业更新成功");

        return ServerResponse.createByErrorMessage("专业更新失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id查找专业
     * @Date 10:23 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping
    @ApiOperation("通过id查找专业")
    @Permission
    public ServerResponse<Major> query(Integer id) {
        Major major = majorService.getById(id);
        if (major != null)
            return ServerResponse.createBySuccess(major);

        return ServerResponse.createByErrorMessage("专业不存在");
    }

    /**
     * @Author 安羽兮
     * @Description 分页查询专业
     * @Date 10:00 2019/12/4
     * @Param [majorQuery]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping("/list")
    @ApiOperation("分页查询专业")
    @Permission
    public ServerResponse queryMajor(MajorQuery majorQuery) {
        PageInfo<Major> list = majorService.pageByQuery(majorQuery);
        if (list != null)
            return ServerResponse.createBySuccess(list);

        return ServerResponse.createByErrorMessage("专业不存在");
    }

    /**
     * @Description: 通过excel批量添加专业
     * @Param: [file]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: ashe
     * @Date: 2019/12/5
     */
    @PostMapping("/batchSave")
    @ApiOperation("批量添加专业")
    @Permission
    public ServerResponse batchSave(MultipartFile file) {
        return ServerResponse.createBySuccess(majorService.saveBachByExcel(file));
    }
}
