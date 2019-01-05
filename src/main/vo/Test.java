package main.vo;

import main.dao.CourseDao;
import main.dao.TeacherCourseDao;
import main.dao.daoImpl.CourseDaoImpl;
import main.dao.daoImpl.TeacherCourseDaoImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by pengweijie on 2018/12/2
 */
public class Test {
    public static void main(String args[]) {
        List<String> list = new ArrayList<String>();
        String s1 = "121";
        String s2 = "11";
        String s3 = "22";
        list.add(s1);
        list.add(s2);
        list.add(s3);
        System.out.println(list.contains(s1));
        CourseDao courseDao = new CourseDaoImpl();
        Course course = null;
        course = courseDao.selectCourseByCourseName("数学");
        TeacherCourseDao teacherCourseDao = new TeacherCourseDaoImpl();
        List<Course> courseList = teacherCourseDao.queryCourseByTeacherId(123);
        System.out.println("4545" + courseList.contains(course));
    }
}
