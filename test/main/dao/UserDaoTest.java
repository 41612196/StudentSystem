package main.dao;

import main.DaoFactory.DaoFactory;
import main.vo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Create by pengweijie on 2018/11/21
 */
public class UserDaoTest {
    @Test
    public void selectUser() throws Exception {
        UserDao userDao = DaoFactory.getUserDaoInstance();
        System.out.println(userDao.selectUser("123", "124"));
    }

    @Test
    public void insertUser() throws Exception {
        User user = new User("41612197", "123", "3");
        UserDao userDao = DaoFactory.getUserDaoInstance();
        userDao.insertUser(user);
    }

    @Test
    public void deleteUser() throws Exception {
        UserDao userDao = DaoFactory.getUserDaoInstance();
        userDao.deleteUser("41612196");
    }

}