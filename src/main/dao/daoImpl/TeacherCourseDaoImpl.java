package main.dao.daoImpl;

import main.dao.TeacherCourseDao;
import main.vo.Classes;
import main.vo.Course;
import main.vo.CourseArranged;
import main.vo.Teacher;
import util.CloseDataAccess;
import util.DataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by pengweijie on 2018/11/29
 */
public class TeacherCourseDaoImpl implements TeacherCourseDao {
    @Override
    public Boolean arrangeCourse(Classes classes, Course course, Teacher teacher) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO course_arrange(classId, className, courseId, courseName, teacherId, teacherName) VALUES (?,?,?,?,?,?)");
            preparedStatement.setInt(1, classes.getClassId());
            preparedStatement.setString(2, classes.getClassName());
            preparedStatement.setInt(3, course.getCourseId());
            preparedStatement.setString(4, course.getCourseName());
            preparedStatement.setInt(5, teacher.getTeacherId());
            preparedStatement.setString(6, teacher.getTeacherName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateInfoTo_teacherCourse(Course course, Teacher teacher) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO teacher_course(courseId, courseName, teacherId, teacherName) VALUES (?,?,?,?)");

            preparedStatement.setInt(1, course.getCourseId());
            preparedStatement.setString(2, course.getCourseName());
            preparedStatement.setInt(3, teacher.getTeacherId());
            preparedStatement.setString(4, teacher.getTeacherName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateInfoTo_teacherClass(Classes classes, Teacher teacher) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO teacher_class(classId, className,teacherId, teacherName) VALUES (?,?,?,?)");
            preparedStatement.setInt(1, classes.getClassId());
            preparedStatement.setString(2, classes.getClassName());
            preparedStatement.setInt(3, teacher.getTeacherId());
            preparedStatement.setString(4, teacher.getTeacherName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteArrangedCourse(int classId, int courseId, int teacherId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM course_arrange WHERE classId=? AND courseId=? AND teacherId=?");
            preparedStatement.setInt(1, classId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.setInt(3, teacherId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            CloseDataAccess.closeDataAccess2(preparedStatement, connection);
        }
        return true;
    }

    @Override
    public List<CourseArranged> getAllArrangedCourse() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CourseArranged> courseArrangedList = new ArrayList<CourseArranged>();
        CourseArranged courseArranged = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM course_arrange");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courseArranged = new CourseArranged();
                courseArranged.setClassId(resultSet.getInt(1));
                courseArranged.setClassName(resultSet.getString(2));
                courseArranged.setCourseId(resultSet.getInt(3));
                courseArranged.setCourseName(resultSet.getString(4));
                courseArranged.setTeacherId(resultSet.getInt(5));
                courseArranged.setTeacherName(resultSet.getString(6));
                courseArrangedList.add(courseArranged);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        }
        return courseArrangedList;
    }

    @Override
    public List<Course> queryCourseByTeacherId(int teacherId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Course> courseList = new ArrayList<Course>();
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT teacher_course.courseId,teacher_course.courseName,credit FROM teacher_course,course " +
                    "WHERE teacher_course.courseId=course.courseId AND teacherId=?");
            preparedStatement.setInt(1, teacherId);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int rowCount = resultSet.getRow();
            System.out.println("该教师课程门数："+rowCount);
            resultSet.beforeFirst();//将光标移到开头


            Course course = null;
            int courseID[] = new int [rowCount];
            String courseName[] = new String[rowCount];
            float credit[] = new float[rowCount];
            String courseType[] = new String[rowCount];

            int i = 0;
            while (resultSet.next()) {//从resultset中得到每一列的值
                courseID[i] = resultSet.getInt(1);
                courseName[i] = resultSet.getString(2);
                credit[i] = resultSet.getFloat(3);

                i++;
            }
            for(int j=0;j<rowCount;j++ ) {//整合每一列的值，还原课程，并添加到课程列表中
                course = new Course();
                course.setCourseId(courseID[j]);
                course.setCourseName(courseName[j]);
                course.setCredit(credit[j]);


                courseList.add(course);
            }
            System.out.println("教师的课：" + courseList);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        }
        return courseList;
    }
}
