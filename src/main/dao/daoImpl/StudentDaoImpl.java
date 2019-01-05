package main.dao.daoImpl;

import main.dao.StudentDao;
import main.vo.Student;
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
public class StudentDaoImpl implements StudentDao {
    @Override
    public boolean insertStudent(Student student) throws Exception {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO student(studentId, studentName, sex, age, major, yearSchool, telephone, address) VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, student.getStudentId());
            preparedStatement.setString(2, student.getStudentName());
            preparedStatement.setString(3, student.getSex());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setString(5, student.getMajor());
            preparedStatement.setInt(6, student.getYearSchool());
            preparedStatement.setString(7, student.getTelephone());
            preparedStatement.setString(8, student.getAddress());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
                preparedStatement=null;
            }
            if (connection != null) {
                connection.close();
                connection=null;
            }
        }
        return true;
    }

    @Override
    public List<Student> getAllStudentsByClassId(int classId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Student> studentList = new ArrayList<Student>();
        Student student = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * from student WHERE studentId IN (SELECT studentId FROM student_class WHERE classId=?)");
            preparedStatement.setInt(1, classId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                student = new Student();
                student.setStudentId(resultSet.getInt(1));
                student.setStudentName(resultSet.getString(2));
                student.setSex(resultSet.getString(3));
                student.setAge(resultSet.getInt(4));
                student.setMajor(resultSet.getString(5));
                student.setYearSchool(resultSet.getInt(6));
                student.setTelephone(resultSet.getString(7));
                student.setAddress(resultSet.getString(8));

                studentList.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public Student findStudentById(int studentId) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Student student=null;
        String sql="SELECT * FROM student WHERE studentId=?";
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, studentId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                student = new Student();
                student.setStudentId(resultSet.getInt(1));
                student.setStudentName(resultSet.getString(2));
                student.setSex(resultSet.getString(3));
                student.setAge(resultSet.getInt(4));
                student.setMajor(resultSet.getString(5));
                student.setYearSchool(resultSet.getInt(6));
                student.setTelephone(resultSet.getString(7));
                student.setAddress(resultSet.getString(8));
                student.setPhoto(resultSet.getString(9));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        }
        return student;
    }

    @Override
    public List<Student> findAll()  {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Student student=null;
        List<Student> stuList = new ArrayList<Student>();
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM student");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                student = new Student();
                student.setStudentId(resultSet.getInt(1));
                student.setStudentName(resultSet.getString(2));
                student.setSex(resultSet.getString(3));
                student.setAge(resultSet.getInt(4));
                student.setMajor(resultSet.getString(5));
                student.setYearSchool(resultSet.getInt(6));
                student.setTelephone(resultSet.getString(7));
                student.setAddress(resultSet.getString(8));

                stuList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        }
        return stuList;
    }

    @Override
    public boolean deleteStudentById(int studentId) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("DELETE from student WHERE studentId=?");
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
           CloseDataAccess.closeDataAccess2(preparedStatement,connection);
        }


    }

    @Override
    public void updateStudent(Student student) throws Exception {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE student SET studentName=?,sex=?,age=?,major=?,yearSchool=? WHERE studentId=?");
            preparedStatement.setString(1, student.getStudentName());
            preparedStatement.setString(2, student.getSex());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getMajor());
            preparedStatement.setInt(5, student.getYearSchool());
            preparedStatement.setInt(6, student.getStudentId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
                preparedStatement=null;
            }
            if (connection != null) {
                connection.close();
                connection=null;
            }
        }

    }
}
