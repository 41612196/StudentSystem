package main.dao;

import main.vo.Classes;
import main.vo.Course;
import main.vo.CourseArranged;
import main.vo.Teacher;

import java.util.List;

/**
 * Create by pengweijie on 2018/11/23
 */
public interface TeacherCourseDao {

    List<Course> queryCourseByTeacherId(int teacherId);//通过教师Id查询他带的课程

    Boolean arrangeCourse(Classes classes, Course course, Teacher teacher);//安排课程

    List<CourseArranged> getAllArrangedCourse();

    Boolean deleteArrangedCourse(int classId, int courseId, int teacherId);

    Boolean updateInfoTo_teacherCourse(Course course, Teacher teacher);

    boolean updateInfoTo_teacherClass(Classes classes, Teacher teacher);

}
