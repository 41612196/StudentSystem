package main.dao;

import main.vo.Course;

import java.util.List;

/**
 * Create by pengweijie on 2018/11/20
 */
public interface CourseDao {
    /**
     * @param courseId 通过课程号查询课程
     * @return 返回类型为课程
     */
    Course selectCourseByCourseId(int courseId);

    /**
     * 通过课程名查询课程
     *
     * @param courseName
     * @return
     */
    Course selectCourseByCourseName(String courseName);

    /**
     * 插入一门课程
     *
     * @param course
     */
    boolean insertCourse(Course course);

    List<Course> getAllCourse();

    boolean deleteCourse(int courseId);

    void updateCourse(Course course);
}
