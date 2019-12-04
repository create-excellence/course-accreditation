package com.excellent.accreditation.untils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.excellent.accreditation.common.exception.ExcelException;

import java.util.Map;

/**
 * @program: course-accreditation
 * @description: 检测内容是否为空
 * @author: ashe
 * @create: 2019-12-04 14:59
 */
public class EmptyCheckUtils {

    public static void checkExcelMap(Map<Integer, String> map,Integer checkLength){
        for(int i =0 ;i<checkLength+1;i++){
            if(StringUtils.isEmpty(map.get(i))){
                throw new ExcelException("数据不完整！");
            }
        }
    }
}
