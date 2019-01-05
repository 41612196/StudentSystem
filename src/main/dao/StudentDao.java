package main.dao;

import main.vo.Classes;
import main.vo.Student;

import java.util.List;

/**
 * Create by pengweijie on 2018/11/19
 */
public interface StudentDao {
    /**
     * @param student 添加的对象为学生
     * @return 是否添加成功的标记
     * @throws Exception
     */
    boolean insertStudent(Student student) throws Exception;

    /**
     * @param studentId 根据学号查询学生
     * @return 返回查询到的学生
     * @throws Exception
     */
    Student findStudentById(int studentId);

    /**
     * 查询所有学生
     *
     * @return
     * @throws Exception
     */
    List<Student> findAll();

    boolean deleteStudentById(int studentId);

    List<Student> getAllStudentsByClassId(int classId);

    void updateStudent(Student student) throws Exception;

}
