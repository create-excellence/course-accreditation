package com.excellent.accreditation.model.vo;

import com.excellent.accreditation.model.entity.CourseClass;
import lombok.Data;

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
        courseClassVo.setTeacher(course);
        courseClassVo.setCourse(teacher);
        courseClassVo.setSemester(semester);

        courseClassVo.setId(courseClass.getId());
        courseClassVo.setNo(courseClass.getNo());
        courseClassVo.setSemesterId(courseClass.getSemesterId());
        courseClassVo.setStatus(courseClass.getCourseId());
        courseClassVo.setStatus(courseClass.getTeacherId());
        courseClassVo.setStatus(courseClass.getStartWeek());
        courseClassVo.setEndWeek(courseClass.getEndWeek());
        courseClassVo.setStatus(courseClass.getStatus());
        courseClassVo.setUpdateTime(courseClass.getUpdateTime());
        courseClassVo.setCreateTime(courseClass.getCreateTime());
        return courseClassVo;
    }
}
