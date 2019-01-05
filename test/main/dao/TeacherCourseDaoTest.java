package main.dao;

import main.dao.daoImpl.TeacherCourseDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Create by pengweijie on 2018/11/29
 */
public class TeacherCourseDaoTest {
    @Test
    public void queryCourseByTeacherId() throws Exception {
        TeacherCourseDao teacherCourseDao = new TeacherCourseDaoImpl();
        teacherCourseDao.queryCourseByTeacherId(123);
    }

}