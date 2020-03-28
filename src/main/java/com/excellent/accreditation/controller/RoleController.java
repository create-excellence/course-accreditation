package com.excellent.accreditation.controller;


import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.model.entity.Role;
import com.excellent.accreditation.model.form.RoleQuery;
import com.excellent.accreditation.service.IRoleService;
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
 * @author evildoer
 * @since 2019-12-07
 */
@RestController
@RequestMapping("/${server.version}/role")
public class RoleController {


    private final IRoleService roleService;

    @Autowired
    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * @Author 安羽兮
     * @Description 添加角色
     * @Date 10:19 2019/11/15
     * @Param [role]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PostMapping
    @ApiOperation("添加角色")
    @Permission
    public ServerResponse create(@RequestBody @NonNull Role role) {
        roleService.create(role);
        return ServerResponse.createBySuccessMessage("角色添加成功");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id删除角色
     * @Date 10:18 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("通过id删除角色")
    @Permission
    public ServerResponse deleteById(@PathVariable("id") Integer id) {
        boolean result = roleService.removeById(id);
        if (result)
            return ServerResponse.createBySuccessMessage("角色删除成功");

        return ServerResponse.createByErrorMessage("角色删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id列表批量删除角色
     * @Date 10:22 2019/11/15
     * @Param [ids]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/deleteByIds")
    @ApiOperation("通过id列表批量删除角色")
    @Permission
    public ServerResponse deleteByIds(@RequestBody @NonNull Collection<Integer> ids) {
        boolean result = roleService.removeByIds(ids);
        if (result)
            return ServerResponse.createBySuccessMessage("角色批量删除成功");

        return ServerResponse.createByErrorMessage("角色批量删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id更新角色
     * @Date 10:49 2019/11/15
     * @Param [id, role]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PutMapping("/{id:\\d+}")
    @ApiOperation("通过id更新角色")
    @Permission
    public ServerResponse updateById(@PathVariable("id") Integer id,
                                     @RequestBody Role role) {
        role.setId(id);
        role.setUpdateTime(LocalDateTime.now());
        boolean result = roleService.updateById(role);
        if (result)
            return ServerResponse.createBySuccessMessage("角色更新成功");

        return ServerResponse.createByErrorMessage("角色更新失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id查找角色
     * @Date 10:23 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping
    @ApiOperation("通过id查找角色")
    @Permission
    public ServerResponse<Role> query(Integer id) {
        Role role = roleService.getById(id);
        if (role != null)
            return ServerResponse.createBySuccess(role);

        return ServerResponse.createByErrorMessage("角色不存在");
    }

    /**
     * @Author 安羽兮
     * @Description 分页查询角色
     * @Date 10:00 2019/12/4
     * @Param [roleQuery]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping("/list")
    @ApiOperation("分页查询角色")
    @Permission
    public ServerResponse queryRole(RoleQuery roleQuery) {
        PageInfo<Role> list = roleService.pageByQuery(roleQuery);
        if (list != null)
            return ServerResponse.createBySuccess(list);

        return ServerResponse.createByErrorMessage("角色不存在");
    }
}
