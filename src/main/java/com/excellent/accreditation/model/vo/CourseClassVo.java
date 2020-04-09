package com.excellent.accreditation.model.vo;

import com.excellent.accreditation.model.entity.CourseClass;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName CourseClassVo
 * @Description TODO
 * @Author 安羽兮
 * @Date 2019/12/1210:30
 * @Version 1.0
 **/
@Data
public class CourseClassVo extends CourseClass {

    /**
     * 课程名称
     */
    private String course;

    /**
     * 课程代码
     */
    private String courseCode;

    /**
     * 老师名字
     */
    private String teacher;

    /**
     * 老师工号
     */
    private String jno;

    /**
     * 学期
     */
    private String semester;

    public static CourseClassVo convert(CourseClass courseClass, String course, String teacher, String semester) {
        CourseClassVo courseClassVo = new CourseClassVo();
        BeanUtils.copyProperties(courseClass, courseClassVo);
        courseClassVo.setCourse(course);
        courseClassVo.setTeacher(teacher);
        courseClassVo.setSemester(semester);
        return courseClassVo;
    }
}
