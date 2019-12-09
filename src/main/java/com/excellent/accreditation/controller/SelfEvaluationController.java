package com.excellent.accreditation.controller;


import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.model.entity.SelfEvaluation;
import com.excellent.accreditation.model.form.SelfEvaluationQuery;
import com.excellent.accreditation.service.ISelfEvaluationService;
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
@RequestMapping("/${server.version}/self-evaluation")
public class SelfEvaluationController {


    private final ISelfEvaluationService selfEvaluationService;

    @Autowired
    public SelfEvaluationController(ISelfEvaluationService selfEvaluationService) {
        this.selfEvaluationService = selfEvaluationService;
    }

    /**
     * @Author 安羽兮
     * @Description 添加评价
     * @Date 10:19 2019/11/15
     * @Param [selfEvaluation]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PostMapping
    @ApiOperation("添加评价")
    @Permission
    public ServerResponse create(@RequestBody @NonNull SelfEvaluation selfEvaluation) {
        selfEvaluationService.create(selfEvaluation);
        return ServerResponse.createBySuccess("评价添加成功");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id删除评价
     * @Date 10:18 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("通过id删除评价")
    @Permission
    public ServerResponse deleteById(@PathVariable("id") Integer id) {
        boolean result = selfEvaluationService.removeById(id);
        if (result)
            return ServerResponse.createBySuccess("评价删除成功");

        return ServerResponse.createByErrorMessage("评价删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id列表批量删除评价
     * @Date 10:22 2019/11/15
     * @Param [ids]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/deleteByIds")
    @ApiOperation("通过id列表批量删除评价")
    @Permission
    public ServerResponse deleteByIds(@RequestBody @NonNull Collection<Integer> ids) {
        boolean result = selfEvaluationService.removeByIds(ids);
        if (result)
            return ServerResponse.createBySuccess("评价批量删除成功");

        return ServerResponse.createByErrorMessage("评价批量删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id更新评价
     * @Date 10:49 2019/11/15
     * @Param [id, selfEvaluation]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PutMapping("/{id:\\d+}")
    @ApiOperation("通过id更新评价")
    @Permission
    public ServerResponse updateById(@PathVariable("id") Integer id,
                                     @RequestBody SelfEvaluation selfEvaluation) {
        selfEvaluation.setId(id);
        selfEvaluation.setUpdateTime(LocalDateTime.now());
        boolean result = selfEvaluationService.updateById(selfEvaluation);
        if (result)
            return ServerResponse.createBySuccess("评价更新成功");

        return ServerResponse.createByErrorMessage("评价更新失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id查找评价
     * @Date 10:23 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping
    @ApiOperation("通过id查找评价")
    @Permission
    public ServerResponse<SelfEvaluation> query(Integer id) {
        SelfEvaluation selfEvaluation = selfEvaluationService.getById(id);
        if (selfEvaluation != null)
            return ServerResponse.createBySuccess(selfEvaluation);

        return ServerResponse.createByErrorMessage("评价不存在");
    }

    /**
     * @Author 安羽兮
     * @Description 分页查询评价
     * @Date 16:52 2019/12/3
     * @Param [selfEvaluationQuery]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping("/list")
    @ApiOperation("分页查询评价")
    @Permission
    public ServerResponse querySelfEvaluation(SelfEvaluationQuery selfEvaluationQuery) {
        PageInfo<SelfEvaluation> list = selfEvaluationService.pageByQuery(selfEvaluationQuery);
        if (list != null)
            return ServerResponse.createBySuccess(list);

        return ServerResponse.createByErrorMessage("评价不存在");
    }
}
