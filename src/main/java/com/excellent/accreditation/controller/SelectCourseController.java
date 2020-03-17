package com.excellent.accreditation.controller;


import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.manage.UserManage;
import com.excellent.accreditation.model.entity.SelectCourse;
import com.excellent.accreditation.model.form.CourseClassQuery;
import com.excellent.accreditation.model.form.SelectCourseQuery;
import com.excellent.accreditation.model.vo.CourseClassVo;
import com.excellent.accreditation.model.vo.SelectCourseVo;
import com.excellent.accreditation.model.vo.UserVo;
import com.excellent.accreditation.service.ICourseClassService;
import com.excellent.accreditation.service.ISelectCourseService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@RequestMapping("/${server.version}/select-course")
public class SelectCourseController {

    private final UserManage userManage;

    private final ICourseClassService courseClassService;

    private final ISelectCourseService selectCourseService;

    /**
     * @author pu
     * @data 2019/12/9
     * description:
     */
    @Autowired
    public SelectCourseController(UserManage userManage, ICourseClassService courseClassService, ISelectCourseService selectCourseService) {
        this.userManage = userManage;
        this.courseClassService = courseClassService;
        this.selectCourseService = selectCourseService;
        ;
    }

    /**
     * @author pu
     * @data 2019/12/9
     * description:
     */
    @PostMapping
    @ApiOperation("添加选课")
    @Permission
    public ServerResponse create(@RequestBody @NonNull SelectCourse selectCourse) {
        // studentId为空表示是学生选课
        if (StringUtils.isEmpty(selectCourse.getStudentId())) {
            UserVo userVo = userManage.getUserInfo();
            selectCourse.setStudentId(userVo.getId());
        }
        selectCourseService.create(selectCourse);
        return ServerResponse.createBySuccess("添加选课");
    }

    /**
     * @author pu
     * @data 2019/12/9
     * description:
     */
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("通过id删除选课")
    @Permission
    public ServerResponse deleteById(@PathVariable("id") Integer id) {
        boolean result = selectCourseService.removeById(id);
        if (result)
            return ServerResponse.createBySuccess("选课删除成功");

        return ServerResponse.createByErrorMessage("选课删除失败");
    }

    /**
     * @author pu
     * @data 2019/12/9
     * description:
     */
    @DeleteMapping("/deleteByIds")
    @ApiOperation("通过id列表批量删除选课")
    @Permission
    public ServerResponse deleteByIds(@NonNull Collection<Integer> ids) {
        boolean result = selectCourseService.removeByIds(ids);
        if (result)
            return ServerResponse.createBySuccess("选课批量删除成功");

        return ServerResponse.createByErrorMessage("选课批量删除失败");
    }

    /**
     * @author pu
     * @data 2019/12/9
     * description:
     */
    @PutMapping("/{id:\\d+}")
    @ApiOperation("通过id更新选课")
    @Permission
    public ServerResponse updateById(@PathVariable("id") Integer id,
                                     @RequestBody SelectCourse selectCourse) {
        selectCourse.setId(id);
        selectCourse.setUpdateTime(LocalDateTime.now());
        selectCourseService.check(selectCourse, Const.UPDATE);
        boolean result = selectCourseService.updateById(selectCourse);
        if (result)
            return ServerResponse.createBySuccess("选课更新成功");

        return ServerResponse.createByErrorMessage("选课更新失败");
    }

    /**
     * @author pu
     * @data 2019/12/9
     * description:
     */
    @GetMapping
    @ApiOperation("通过id查找选课")
    @Permission
    public ServerResponse<SelectCourseVo> query(Integer id) {
        SelectCourseVo selectCourseVo = selectCourseService.selectCourseId(id);
        if (selectCourseVo != null) {
            return ServerResponse.createBySuccess(selectCourseVo);
        }
        return ServerResponse.createByErrorMessage("选课不存在");
    }


    @GetMapping("/list-student")
    @ApiOperation("通过学生id查找选课")
//    @Permission(roles = {Const.STUDENT})
    public ServerResponse queryByStudentId(SelectCourseQuery selectCourseQuery) {
        UserVo userVo = userManage.getUserInfo();
        selectCourseQuery.setStudentId(userVo.getId());
        PageInfo<SelectCourseVo> list = selectCourseService.pageSelectByStudentId(selectCourseQuery);
        if (list != null)
            return ServerResponse.createBySuccess(list);

        return ServerResponse.createByErrorMessage("选课不存在");
    }

    @GetMapping("/select")
    @ApiOperation("查找所有该登陆学生未选的课")
//    @Permission(roles = {Const.STUDENT})
    public ServerResponse queryByStudentId(CourseClassQuery courseClassQuery) {
        UserVo userVo = userManage.getUserInfo();
        PageInfo<CourseClassVo> list = courseClassService.pageSelectByStudentId(courseClassQuery, userVo.getId());
        if (list != null)
            return ServerResponse.createBySuccess(list);

        return ServerResponse.createByErrorMessage("选课不存在");
    }


    /**
     * @author pu
     * @data 2019/12/9
     * description:
     */
    @GetMapping("/list")
    @ApiOperation("分页查询选课")
    @Permission
    public ServerResponse querySelectCourse(SelectCourseQuery selectCourseQuery) {
        PageInfo<SelectCourseVo> list = selectCourseService.pageByQuery(selectCourseQuery);
        if (list != null)
            return ServerResponse.createBySuccess(list);

        return ServerResponse.createByErrorMessage("选课不存在");
    }


    /**
     * @author pu
     * @data 2019/12/9
     * description:
     */
    @PostMapping("/batchSave")
    @ApiOperation("批量添加选课")
    @Permission
    public ServerResponse batchSave(MultipartFile file) {
        return ServerResponse.createBySuccess(selectCourseService.saveBachByExcel(file));
    }
}
