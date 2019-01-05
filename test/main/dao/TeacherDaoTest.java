package main.dao;

import main.DaoFactory.DaoFactory;
import main.vo.Teacher;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Create by pengweijie on 2018/11/19
 */
public class TeacherDaoTest {
    @Test
    public void deleteById() throws Exception {
        TeacherDao teacherDao= DaoFactory.getTeacherDaoInstance();
        teacherDao.deleteById(1);
    }

    @Test
    public void insertTeacher() throws Exception {
        Teacher teacher = new Teacher(1, "王五", "男", "化学化工学院", "讲师", "硕士");
        TeacherDao teacherDao=DaoFactory.getTeacherDaoInstance();
        teacherDao.insertTeacher(teacher);

    }

    @Test
    public void findTeacherById() throws Exception {
        TeacherDao teacherDao=DaoFactory.getTeacherDaoInstance();
        Teacher teacher=teacherDao.findTeacherById(2);
        System.out.println(teacher);

    }

    @Test
    public void updateTeacher() throws Exception {
        TeacherDao teacherDao=DaoFactory.getTeacherDaoInstance();
        Teacher teacher = new Teacher(2, "李四", "男", "化学化工学院", "讲师", "硕士");
        teacherDao.updateTeacher(teacher);
        System.out.println(teacherDao.findTeacherById(2));
    }

}