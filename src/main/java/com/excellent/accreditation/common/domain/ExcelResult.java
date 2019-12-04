package com.excellent.accreditation.common.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: course-accreditation
 * @description: 上传Excel插入的结果集
 * @author: ashe
 * @create: 2019-12-04 14:10
 */
@Data
public class ExcelResult {
    /**
     * 序号
     */
    private Integer no;
    /**
     * 状态 0-添加成功 1-添加失败
     */
    private Integer status;

    /**
     * 操作信息
     */
    private String message;

    /**
     * 操作时间
     */
    private LocalDateTime createTime;

    public ExcelResult(){
        status=Const.FAIL_INCREASE;  //默认为失败
        createTime=LocalDateTime.now();
    }
}
