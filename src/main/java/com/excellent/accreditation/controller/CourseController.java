package com.excellent.accreditation.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ashe
 * @since 2019-11-14
 */
@RestController
@RequestMapping("/{version}/course")
public class CourseController {

    // 访问路径为 http://localhost:8888/v1/course/test
    @RequestMapping("/test")
    private String test() {
        return "success";
    }
}
