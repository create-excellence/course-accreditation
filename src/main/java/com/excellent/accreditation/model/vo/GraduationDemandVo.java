package com.excellent.accreditation.model.vo;

import com.excellent.accreditation.model.entity.GraduationDemand;
import lombok.Data;

/**
 * @ClassName GraduationDemandVo
 * @Description TODO
 * @Author 安羽兮
 * @Date 2019/12/1218:54
 * @Version 1.0
 **/
@Data
public class GraduationDemandVo extends GraduationDemand {
    private String major;
}
