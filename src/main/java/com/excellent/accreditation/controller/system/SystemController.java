package com.excellent.accreditation.controller.system;


import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.manage.UserManage;
import com.excellent.accreditation.model.form.LoginForm;
import com.excellent.accreditation.model.vo.UserVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ashe
 * @since 2019-11-14
 */
@RestController
@RequestMapping("/${server.version}/system/")
public class SystemController {

    private final UserManage userManage;

    public SystemController(UserManage userManage) {
        this.userManage = userManage;
    }

    @PostMapping("login")
    @ApiOperation("登录")
    public ServerResponse login(@RequestBody @NonNull LoginForm loginForm) {
        UserVo userVo = userManage.login(loginForm.getCode(), loginForm.getPassword());
        // 操作成功
        if (userVo != null)
            return ServerResponse.createBySuccessMessage("登录成功", userVo);

        return ServerResponse.createByErrorMessage("登录失败");
    }

    @GetMapping("getUserInfo")
    @ApiOperation("获取用户信息")
    public ServerResponse getUserInfo() {
        UserVo userVo = userManage.getUserInfo();
        // 操作成功
        if (userVo != null)
            return ServerResponse.createBySuccessMessage("用户信息获取成功", userVo);

        return ServerResponse.createByErrorMessage("用户信息获取失败");
    }

    @PutMapping("updateUserInfo")
    @ApiOperation("用户修改信息")
    public ServerResponse getUserInfo(@RequestBody UserVo userVo) {
        if (userManage.updateUserInfo(userVo))
            return ServerResponse.createBySuccessMessage("修改成功");

        return ServerResponse.createByErrorMessage("修改失败");
    }


    @PostMapping("register")
    @ApiOperation("注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "工号或学号", required = true, paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form"),
            @ApiImplicitParam(name = "role", value = "角色", required = true, paramType = "form")
    })
    public ServerResponse register(@NonNull String code,
                                   @NonNull String password,
                                   @NonNull String role) {
        return userManage.register(code, password, role);
    }
}
