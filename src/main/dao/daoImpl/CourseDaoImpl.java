package main.dao.daoImpl;

import main.dao.CourseDao;
import main.vo.Course;
import util.CloseDataAccess;
import util.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by pengweijie on 2018/11/20
 */
public class CourseDaoImpl implements CourseDao {
    @Override
    public boolean deleteCourse(int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM course WHERE courseId=?");
            preparedStatement.setInt(1, courseId);
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
    public Course selectCourseByCourseId(int courseId) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Course course=null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * from course WHERE courseId=?");
            preparedStatement.setInt(1, courseId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                course = new Course();
                course.setCourseId(resultSet.getInt(1));
                course.setCourseName(resultSet.getString(2));
                course.setCredit(resultSet.getFloat(3));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        }
        return course;
    }

    @Override
    public Course selectCourseByCourseName(String courseName) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Course course=null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * from course WHERE courseName=?");
            preparedStatement.setString(1, courseName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                course = new Course();
                course.setCourseId(resultSet.getInt(1));
                course.setCourseName(resultSet.getString(2));
                course.setCredit(resultSet.getFloat(3));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        }
        return course;
    }

    @Override
    public List<Course> getAllCourse() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Course> courseList = new ArrayList<Course>();
        ResultSet resultSet = null;
        Course course = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM course");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                course = new Course();
                course.setCourseId(resultSet.getInt(1));
                course.setCourseName(resultSet.getString(2));
                course.setCredit(resultSet.getFloat(3));
                courseList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseDataAccess.closeDataAccess3(resultSet,preparedStatement,connection);

        }
        return courseList;

    }

    @Override
    public boolean insertCourse(Course course) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO course(courseId, courseName, credit) VALUES (?,?,?)");
            preparedStatement.setInt(1, course.getCourseId());
            preparedStatement.setString(2, course.getCourseName());
            preparedStatement.setFloat(3, course.getCredit());

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
    public void updateCourse(Course course) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE course SET courseId=?,courseName=?,credit=?,courseType=?");
            preparedStatement.setInt(1, course.getCourseId());
            preparedStatement.setString(2, course.getCourseName());
            preparedStatement.setFloat(3, course.getCredit());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseDataAccess.closeDataAccess2(preparedStatement, connection);
        }
    }
}
