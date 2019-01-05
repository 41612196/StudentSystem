package main.dao;

import main.vo.User;

import java.util.List;

/**
 * Create by pengweijie on 2018/11/21
 */
public interface UserDao {
    List<User> selectAllUser();
    User selectUser(String username, String password);

    boolean insertUser(User user);

    /**
     * 根据username删除用户
     * @param username
     * @return
     */
    boolean deleteUser(String username);

    boolean addUser(String username, String password, String superuser);

}
