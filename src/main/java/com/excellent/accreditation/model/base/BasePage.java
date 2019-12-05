package com.excellent.accreditation.model.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName PageBase
 * @Description TODO
 * @Author 安羽兮
 * @Date 2019/12/315:52
 * @Version 1.0
 **/
@Data
public class BasePage implements Serializable {

    private static final long serialVersionUID = -4869594085374385813L;

    /**
     * 总数
     */
    private long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    private int pageSize = 10;

    /**
     * 当前页
     */
    private int page = 1;
}
