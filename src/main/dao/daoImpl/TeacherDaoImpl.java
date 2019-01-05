package main.dao.daoImpl;

import main.dao.ScoreDao;
import main.dao.TeacherDao;
import main.vo.Score;
import main.vo.Student;
import main.vo.Teacher;
import util.CloseDataAccess;
import util.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * Create by pengweijie on 2018/11/19
 */
public class TeacherDaoImpl implements TeacherDao {
    @Override
    public List<Teacher> getAllTeacher() {
        List<Teacher> teacherList = new ArrayList<Teacher>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Teacher teacher = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM teacher");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teacher = new Teacher();
                teacher.setTeacherId(resultSet.getInt(1));
                teacher.setTeacherName(resultSet.getString(2));
                teacher.setSex(resultSet.getString(3));
                teacher.setCollege(resultSet.getString(4));
                teacher.setProfessionalTitle(resultSet.getString(5));
                teacher.setDegree(resultSet.getString(5));
                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        }
        return teacherList;
    }

    @Override
    public boolean deleteById(int teacherId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("DELETE from teacher WHERE teacherId=?");
            preparedStatement.setInt(1, teacherId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
                CloseDataAccess.closeDataAccess2(preparedStatement, connection);
        }
        return true;
    }

    @Override
    public boolean insertTeacher(Teacher teacher) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO teacher(teacherId, teacherName, sex, college, professionalTitle, degree) VALUES (?,?,?,?,?,?)");
            preparedStatement.setInt(1, teacher.getTeacherId());
            preparedStatement.setString(2, teacher.getTeacherName());
            preparedStatement.setString(3, teacher.getSex());
            preparedStatement.setString(4, teacher.getCollege());
            preparedStatement.setString(5, teacher.getProfessionalTitle());
            preparedStatement.setString(6, teacher.getDegree());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            CloseDataAccess.closeDataAccess2(preparedStatement, connection);

        }
        return true;
    }

    @Override
    public Teacher findTeacherById(int teacherId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Teacher teacher = new Teacher();
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * from teacher WHERE teacherId=?");
            preparedStatement.setInt(1, teacherId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teacher.setTeacherId(resultSet.getInt(1));
                teacher.setTeacherName(resultSet.getString(2));
                teacher.setSex(resultSet.getString(3));
                teacher.setCollege(resultSet.getString(4));
                teacher.setProfessionalTitle(resultSet.getString(5));
                teacher.setDegree(resultSet.getString(6));
                teacher.setPhoto(resultSet.getString(7));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        }
        return teacher;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE teacher SET teacherName=?,sex=?,college=?,professionalTitle=?,degree=? WHERE teacherId=?");
            preparedStatement.setString(1, teacher.getTeacherName());
            preparedStatement.setString(2, teacher.getSex());
            preparedStatement.setString(3, teacher.getCollege());
            preparedStatement.setString(4, teacher.getProfessionalTitle());
            preparedStatement.setString(5, teacher.getDegree());
            preparedStatement.setInt(6, teacher.getTeacherId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public List<Integer> getAllStudentIdByTeacherId(int teacherId) {//根据教师Id得到他的所有学生的学号
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Integer> studentIdList = new ArrayList<Integer>();
        System.out.println("调用了这个函数");
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT studentId from student_class,teacher_class WHERE student_class.classId=teacher_class.classId AND teacherId=?");
            preparedStatement.setInt(1, teacherId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("进入了while");
                int studentId = resultSet.getInt(1);
                System.out.println("学生：" + studentId);
                studentIdList.add(studentId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        }
        return studentIdList;
    }

    @Override
    /**
     * 到Score表中查询到该教师所有学生成绩(指定课程）
     */
    public List<Score> getAllScoreByTeacherIdAndCourseIdFromScore(int teacherId, int courseId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Score> scoreList = new ArrayList<Score>();

        List<Integer> studentIdList = null;
        studentIdList = getAllStudentIdByTeacherId(teacherId);
        System.out.println("该教师的学生" + studentIdList);

        Score score = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM score WHERE studentId=? AND courseId=?");
            preparedStatement.setInt(2, courseId);
            for (Integer studentId :
                    studentIdList) {
                preparedStatement.setInt(1, studentId);

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    score = new Score();
                    score.setStudentId(resultSet.getInt(1));
                    score.setCourseId(resultSet.getInt(2));
                    score.setStudentName(resultSet.getString(3));
                    score.setCourseName(resultSet.getString(4));
                    score.setGrade(resultSet.getInt(5));
                    scoreList.add(score);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return scoreList;
    }


}
