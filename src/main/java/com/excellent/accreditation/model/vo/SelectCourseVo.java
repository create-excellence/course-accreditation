package com.excellent.accreditation.model.vo;

import com.excellent.accreditation.model.entity.CourseClass;
import com.excellent.accreditation.model.entity.SelectCourse;
import lombok.Data;

@Data
public class SelectCourseVo extends SelectCourse {

    /**
     * 课程名称
     */
    private String course;


    private String sno;
    /**
     * 学生姓名
     */
    private String student;

    /**
     * 老师名字
     */
    private String teacher;

    /**
     * 学期
     */
    private String semester;

    private CourseClass courseClass;


    private String no;

    /**
     * 学期id
     */
    private Integer semesterId;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 老师id
     */
    private Integer teacherId;

    private Integer startWeek;

    private Integer endWeek;

    /**
     * 0-开课 1-未开课
     */
    private Integer status;


    public static SelectCourseVo selectCourseVo(String sno, String student, CourseClass courseClass, String course, String teacher, String semester){
        SelectCourseVo selectCourseVo =new SelectCourseVo();
        selectCourseVo.setSno(sno);
        selectCourseVo.setSemester(semester);
        selectCourseVo.setCourse(course);
        selectCourseVo.setStudent(student);
        selectCourseVo.setStudent(teacher);
        return selectCourseVo;
    }


}
