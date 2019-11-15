package com.excellent.accreditation.controller.system;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ashe
 * @since 2019-11-14
 */
@RestController
@RequestMapping("/${server.version}/system")
public class SystemController {

    // 访问路径为 http://localhost:8888/v1/system/test
    @RequestMapping("/test")
    private String test() {
//        int i = 1 / 0;      // 测试controller异常捕获

        return "success";
    }
}
