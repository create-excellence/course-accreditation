package com.excellent.accreditation.controller;


import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.model.entity.Semester;
import com.excellent.accreditation.model.form.SemesterQuery;
import com.excellent.accreditation.service.ISemesterService;
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
@RequestMapping("/${server.version}/semester")
public class SemesterController {


    private final ISemesterService semesterService;

    @Autowired
    public SemesterController(ISemesterService semesterService) {
        this.semesterService = semesterService;
    }

    /**
     * @Author 安羽兮
     * @Description 添加学期
     * @Date 10:19 2019/11/15
     * @Param [semester]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PostMapping
    @ApiOperation("添加学期")
    @Permission
    public ServerResponse create(@RequestBody @NonNull Semester semester) {
        semesterService.create(semester);
        return ServerResponse.createBySuccess("学期添加成功");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id删除学期
     * @Date 10:18 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("通过id删除学期")
    @Permission
    public ServerResponse deleteById(@PathVariable("id") Integer id) {
        boolean result = semesterService.removeById(id);
        if (result)
            return ServerResponse.createBySuccess("学期删除成功");

        return ServerResponse.createByErrorMessage("学期删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id列表批量删除学期
     * @Date 10:22 2019/11/15
     * @Param [ids]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/deleteByIds")
    @ApiOperation("通过id列表批量删除学期")
    @Permission
    public ServerResponse deleteByIds(@RequestBody @NonNull Collection<Integer> ids) {
        boolean result = semesterService.removeByIds(ids);
        if (result)
            return ServerResponse.createBySuccess("学期批量删除成功");

        return ServerResponse.createByErrorMessage("学期批量删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id更新学期
     * @Date 10:49 2019/11/15
     * @Param [id, semester]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PutMapping("/{id:\\d+}")
    @ApiOperation("通过id更新学期")
    @Permission
    public ServerResponse updateById(@PathVariable("id") Integer id,
                                     @RequestBody Semester semester) {
        semester.setId(id);
        semester.setUpdateTime(LocalDateTime.now());
        boolean result = semesterService.updateById(semester);
        if (result)
            return ServerResponse.createBySuccess("学期更新成功");

        return ServerResponse.createByErrorMessage("学期更新失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id查找学期
     * @Date 10:23 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping
    @ApiOperation("通过id查找学期")
    @Permission
    public ServerResponse<Semester> query(Integer id) {
        Semester semester = semesterService.getById(id);
        if (semester != null)
            return ServerResponse.createBySuccess(semester);

        return ServerResponse.createByErrorMessage("学期不存在");
    }

    /**
     * @Author 安羽兮
     * @Description 分页查询学期
     * @Date 16:52 2019/12/3
     * @Param [semesterQuery]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping("/list")
    @ApiOperation("分页查询学期")
    @Permission
    public ServerResponse querySemester(SemesterQuery semesterQuery) {
        PageInfo<Semester> list = semesterService.pageByQuery(semesterQuery);
        if (list != null)
            return ServerResponse.createBySuccess(list);

        return ServerResponse.createByErrorMessage("学期不存在");
    }

    /**
     * @Description: 通过excel批量添加学期
     * @Param: [file]
     * @Return: com.excellent.accreditation.common.domain.ServerResponse
     * @Author: ashe
     * @Date: 2019/12/5
     */
    @PostMapping("/batchSave")
    @ApiOperation("批量添加学期")
    @Permission
    public ServerResponse batchSave(MultipartFile file) {
        return ServerResponse.createBySuccess(semesterService.saveBachByExcel(file));
    }
}
