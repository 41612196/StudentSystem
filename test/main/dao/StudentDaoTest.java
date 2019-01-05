package main.dao;

import main.DaoFactory.DaoFactory;
import main.vo.Student;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Create by pengweijie on 2018/11/19
 */
public class StudentDaoTest {
    @Test
    public void updateStudent() throws Exception {
        Student student = new Student(3, "张三", "男", "软件工程", 19, 2005);
        StudentDao studentDao=DaoFactory.getStudentDaoInstance();
        studentDao.updateStudent(student);

    }

    @Test
    public void insertStudent() throws Exception {
        Student student = new Student(3, "张三", "男", "软件工程", 19, 2005);
        StudentDao studentDao=DaoFactory.getStudentDaoInstance();
        studentDao.insertStudent(student);
    }

    @Test
    public void findAll() throws Exception {
        List<Student> studentList=null;
        StudentDao studentDao= DaoFactory.getStudentDaoInstance();
        studentList=studentDao.findAll();
        for (Student student:
             studentList) {
            System.out.println(student);
        }

    }

}