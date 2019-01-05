package main.dao.daoImpl;

import main.dao.ClassesDao;
import main.vo.Classes;
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
 * Create by pengweijie on 2018/11/21
 */
public class ClassesDaoImpl implements ClassesDao {
    @Override
    public boolean addStudentToClass(Student student, Classes classes) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO student_class(studentId, studentName, classId, className) VALUES (?,?,?,?)");
            preparedStatement.setInt(1, student.getStudentId());
            preparedStatement.setString(2, student.getStudentName());
            preparedStatement.setInt(3, classes.getClassId());
            preparedStatement.setString(4, classes.getClassName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Classes> getAllClasses() {
        List<Classes> classesList = new ArrayList<Classes>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Classes classes = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM class");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                classes = new Classes();
                classes.setClassId(resultSet.getInt(1));
                classes.setClassName(resultSet.getString(2));
                classes.setOfCollege(resultSet.getString(3));
                classesList.add(classes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        }
        return classesList;
    }

    @Override
    public Classes selectClassByClassId(int classId) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Classes classes = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM class WHERE classId=?");
            preparedStatement.setInt(1, classId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                classes = new Classes();
                classes.setClassId(resultSet.getInt(1));
                classes.setClassName(resultSet.getString(2));
                classes.setOfCollege(resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        }
        return classes;
    }

    @Override
    public boolean insertClass(Classes classes) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO class(classId, className, ofCollege) VALUES (?,?,?)");
            preparedStatement.setInt(1, classes.getClassId());
            preparedStatement.setString(2, classes.getClassName());
            preparedStatement.setString(3, classes.getOfCollege());
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
    public boolean deleteClass(Classes classes) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM class WHERE classId=? OR className=?");
            preparedStatement.setInt(1, classes.getClassId());
            preparedStatement.setString(2, classes.getClassName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            CloseDataAccess.closeDataAccess2(preparedStatement, connection);
        }
        return true;
    }
}
