package main.service;

import main.vo.*;

import java.util.List;

/**
 * Create by pengweijie on 2018/11/21
 */
public interface AdminService {


    void deleteTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher);

    boolean addStudent(Student student);

    boolean addStudentToClass(Student student, Classes classes);
    void deleteStudent(Student student);

    void updateStudent(Student student);

    List<Course> getAllCourse();

    Boolean arrangeCourse(Classes classes, Course course, Teacher teacher);

    List<Student> getAllStudent(int classId);

    boolean addTeacher(Teacher teacher);
    Boolean deleteArrangedCourse(int classId, int courseId, int teacherId);

    boolean addCourse(Course course);

    boolean deleteCourse(int courseId);

    boolean addClass(Classes classes);

    Teacher findTeacherByTeacherId(int teacherId);

    boolean deleteClass(int classesId);

    List<Classes> getAllClasses();

    boolean deleteTeacher(int teacherId);

    List<Teacher> getAllTeacher();

    List<User> getAllUser();

    boolean deleteUser(String username);

    boolean addUser(String username, String password, String superuser);

    Boolean updateInfoTo_teacherCourse(Course course, Teacher teacher);

    boolean updateInfoTo_teacherClass(Classes classes, Teacher teacher);
}
