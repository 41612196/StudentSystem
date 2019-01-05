package main.service.serviceImpl;

import main.DaoFactory.DaoFactory;
import main.dao.UserDao;
import main.service.LoginService;
import main.vo.User;

/**
 * Create by pengweijie on 2018/11/21
 */
public class LoginServiceImpl implements LoginService {
    @Override
    public String login(String username, String password) {
        UserDao userDao = DaoFactory.getUserDaoInstance();
        User user = userDao.selectUser(username, password);

        return  user.getSuperuser();//返回查询到用户的等级权限，若为空，证明用户信息错误或不存在用户，否则根据权限的等级判断是啥用户


    }

}
