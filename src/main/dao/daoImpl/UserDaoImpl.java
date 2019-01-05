package main.dao.daoImpl;

import main.dao.UserDao;
import main.vo.User;
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
public class UserDaoImpl implements UserDao {
    @Override
    public List<User> selectAllUser() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        List<User> userList = new ArrayList<User>();
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user ");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setUsername(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                user.setSuperuser(resultSet.getString(3));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User selectUser(String username, String password) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        User user1=null;
        try {
            user1 = new User();
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {
                user1.setUsername(resultSet.getString(1));
                user1.setPassword(resultSet.getString(2));
                user1.setSuperuser(resultSet.getString(3));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        }
        return user1;
    }

    @Override
    public boolean insertUser(User user) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO user(username, password, superuser) VALUES (?,?,?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSuperuser());
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
    public boolean addUser(String username, String password, String superuser) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO user(username, password, superuser) VALUES (?,?,?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, superuser);
            System.out.println("插入的是："+username+password+superuser);
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
    public boolean deleteUser(String username) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM user WHERE username=?");
            preparedStatement.setString(1, username);
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
