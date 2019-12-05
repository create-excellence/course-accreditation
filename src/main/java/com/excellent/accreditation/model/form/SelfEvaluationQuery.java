package com.excellent.accreditation.model.form;

import com.excellent.accreditation.model.base.BasePage;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @ClassName CourseQuery
 * @Description TODO
 * @Author 安羽兮
 * @Date 2019/12/316:18
 * @Version 1.0
 **/
@Data
public class SelfEvaluationQuery extends BasePage {

    @Size(max = 25, message = "评价档次的字符长度不能超过 {max}")
    private String answer;
    /**
     * @Description 得分
     * @Date 14:45 2019/12/5
     **/
    private Integer score;
}
