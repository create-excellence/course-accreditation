package com.excellent.accreditation.controller;


import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.domain.ServerResponse;
import com.excellent.accreditation.model.entity.Questionnaire;
import com.excellent.accreditation.model.form.QuestionnaireQuery;
import com.excellent.accreditation.service.IQuestionnaireService;
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
@RequestMapping("/${server.version}/questionnaire")
public class QuestionnaireController {


    private final IQuestionnaireService questionnaireService;

    @Autowired
    public QuestionnaireController(IQuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    /**
     * @Author 安羽兮
     * @Description 添加问卷
     * @Date 10:19 2019/11/15
     * @Param [questionnaire]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PostMapping
    @ApiOperation("添加问卷")
    @Permission
    public ServerResponse create(@RequestBody @NonNull Questionnaire questionnaire) {
        questionnaireService.create(questionnaire);
        return ServerResponse.createBySuccess("问卷添加成功");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id删除问卷
     * @Date 10:18 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("通过id删除问卷")
    @Permission
    public ServerResponse deleteById(@PathVariable("id") Integer id) {
        boolean result = questionnaireService.removeById(id);
        if (result)
            return ServerResponse.createBySuccess("问卷删除成功");

        return ServerResponse.createByErrorMessage("问卷删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id列表批量删除问卷
     * @Date 10:22 2019/11/15
     * @Param [ids]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @DeleteMapping("/deleteByIds")
    @ApiOperation("通过id列表批量删除问卷")
    @Permission
    public ServerResponse deleteByIds(@NonNull Collection<Integer> ids) {
        boolean result = questionnaireService.removeByIds(ids);
        if (result)
            return ServerResponse.createBySuccess("问卷批量删除成功");

        return ServerResponse.createByErrorMessage("问卷批量删除失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id更新问卷
     * @Date 10:49 2019/11/15
     * @Param [id, questionnaire]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @PutMapping("/{id:\\d+}")
    @ApiOperation("通过id更新问卷")
    @Permission
    public ServerResponse updateById(@PathVariable("id") Integer id,
                                     @RequestBody Questionnaire questionnaire) {
        questionnaire.setId(id);
        questionnaire.setUpdateTime(LocalDateTime.now());
        boolean result = questionnaireService.updateById(questionnaire);
        if (result)
            return ServerResponse.createBySuccess("问卷更新成功");

        return ServerResponse.createByErrorMessage("问卷更新失败");
    }

    /**
     * @Author 安羽兮
     * @Description 通过id查找问卷
     * @Date 10:23 2019/11/15
     * @Param [id]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping
    @ApiOperation("通过id查找问卷")
    @Permission
    public ServerResponse<Questionnaire> query(Integer id) {
        Questionnaire questionnaire = questionnaireService.getById(id);
        if (questionnaire != null)
            return ServerResponse.createBySuccess(questionnaire);

        return ServerResponse.createByErrorMessage("问卷不存在");
    }

    /**
     * @Author 安羽兮
     * @Description 分页查询问卷
     * @Date 16:52 2019/12/3
     * @Param [questionnaireQuery]
     * @Return com.excellent.accreditation.common.domain.ServerResponse
     **/
    @GetMapping("/list")
    @ApiOperation("分页查询问卷")
    @Permission
    public ServerResponse queryQuestionnaire(QuestionnaireQuery questionnaireQuery) {
        PageInfo<Questionnaire> list = questionnaireService.pageByQuery(questionnaireQuery);
        if (list != null)
            return ServerResponse.createBySuccess(list);

        return ServerResponse.createByErrorMessage("问卷不存在");
    }
}
