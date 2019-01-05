package main.service.serviceImpl;

import main.DaoFactory.DaoFactory;
import main.dao.*;
import main.dao.daoImpl.*;
import main.service.AdminService;
import main.vo.*;

import java.util.List;

/**
 * Create by pengweijie on 2018/11/22
 */
public class AdminServiceImpl implements AdminService {
    private TeacherDao teacherDao = DaoFactory.getTeacherDaoInstance();
    private StudentDao studentDao = DaoFactory.getStudentDaoInstance();

    @Override
    public boolean addClass(Classes classes) {
        ClassesDao classesDao = new ClassesDaoImpl();
        return classesDao.insertClass(classes);
    }

    @Override
    public Teacher findTeacherByTeacherId(int teacherId) {
        TeacherDao teacherDao = new TeacherDaoImpl();
        return teacherDao.findTeacherById(teacherId);
    }

    @Override
    public boolean deleteClass(int classesId) {
        ClassesDao classesDao = new ClassesDaoImpl();
        return classesDao.deleteClass(classesDao.selectClassByClassId(classesId));
    }

    @Override
    public List<Classes> getAllClasses() {
        ClassesDao classesDao = new ClassesDaoImpl();
        return classesDao.getAllClasses();
    }

    @Override
    public boolean deleteTeacher(int teacherId) {
        TeacherDao teacherDao = new TeacherDaoImpl();
        return teacherDao.deleteById(teacherId);

    }

    @Override
    public List<Teacher> getAllTeacher() {
        TeacherDao teacherDao = new TeacherDaoImpl();
        return teacherDao.getAllTeacher();
    }

    @Override
    public boolean deleteUser(String username) {
        UserDao userDao = new UserDaoImpl();
        return userDao.deleteUser(username);
    }

    @Override
    public boolean addUser(String username, String password, String superuser) {
        UserDao userDao = new UserDaoImpl();
        return userDao.addUser(username, password, superuser);
    }

    @Override
    public List<User> getAllUser() {
        UserDao userDao = new UserDaoImpl();
        return userDao.selectAllUser();

    }

    @Override
    public List<Student> getAllStudent(int classId) {
        StudentDao studentDao = new StudentDaoImpl();
        return studentDao.getAllStudentsByClassId(classId);

    }

    @Override
    public List<Course> getAllCourse() {
        CourseDao courseDao = new CourseDaoImpl();
        return courseDao.getAllCourse();
    }

    @Override
    public boolean addCourse(Course course) {
        CourseDao courseDao = new CourseDaoImpl();
        return courseDao.insertCourse(course);
    }

    @Override
    public boolean deleteCourse(int courseId) {
        CourseDao courseDao = new CourseDaoImpl();
        return courseDao.deleteCourse(courseId);
    }

    @Override
    public boolean addTeacher(Teacher teacher) {

        return teacherDao.insertTeacher(teacher);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        TeacherDao teacherDao = DaoFactory.getTeacherDaoInstance();
        teacherDao.deleteById(teacher.getTeacherId());

    }

    @Override
    public void updateTeacher(Teacher teacher) {

        teacherDao.updateTeacher(teacher);

    }

    @Override
    public boolean addStudentToClass(Student student, Classes classes) {
        ClassesDao classesDao = new ClassesDaoImpl();
        return classesDao.addStudentToClass(student, classes);
    }

    @Override
    public boolean addStudent(Student student) {

        try {
            studentDao.insertStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public void deleteStudent(Student student) {

        try {
            studentDao.deleteStudentById(student.getStudentId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(Student student) {

        try {
            studentDao.updateStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean deleteArrangedCourse(int classId, int courseId, int teacherId) {
        TeacherCourseDao teacherCourseDao = new TeacherCourseDaoImpl();
        return teacherCourseDao.deleteArrangedCourse(classId, courseId, teacherId);
    }

    @Override
    public Boolean arrangeCourse(Classes classes, Course course, Teacher teacher) {
        TeacherCourseDao teacherCourseDao = new TeacherCourseDaoImpl();
        return teacherCourseDao.arrangeCourse(classes, course, teacher);

    }

    @Override
    public Boolean updateInfoTo_teacherCourse(Course course, Teacher teacher) {
        TeacherCourseDao teacherCourseDao = new TeacherCourseDaoImpl();
        return teacherCourseDao.updateInfoTo_teacherCourse(course,teacher);
    }

    @Override
    public boolean updateInfoTo_teacherClass(Classes classes, Teacher teacher) {
        TeacherCourseDao teacherCourseDao = new TeacherCourseDaoImpl();
        return teacherCourseDao.updateInfoTo_teacherClass(classes,teacher);
    }
}
