package com.excellent.course_accreditation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.excellent.dao")
@ComponentScan("com.excellent")
public class CourseAccreditationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseAccreditationApplication.class, args);
    }

}
