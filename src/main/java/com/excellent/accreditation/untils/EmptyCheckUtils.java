package com.excellent.accreditation.untils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.excellent.accreditation.common.domain.ExcelResult;
import com.excellent.accreditation.common.exception.ExcelException;

import java.util.Map;

/**
 * @program: course-accreditation
 * @description: 检测内容是否为空
 * @author: ashe
 * @create: 2019-12-04 14:59
 */
public class EmptyCheckUtils {

    public static void checkExcelMapAndSetNo(Map<Integer, String> map, ExcelResult excelResult, Integer checkLength){
        for(int i =0 ;i<checkLength+1;i++){
            if(StringUtils.isEmpty(map.get(i))){
                if(i==0){
                    throw new ExcelException("序号不能为空！");
                }
                throw new ExcelException("数据不完整！");
            }
            if (i==0){
                excelResult.setNo(Integer.parseInt(map.get(i)));
            }
        }
    }
}
