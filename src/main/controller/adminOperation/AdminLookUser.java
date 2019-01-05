package main.controller.adminOperation;

import main.dao.UserDao;
import main.dao.daoImpl.UserDaoImpl;
import main.service.AdminService;
import main.service.serviceImpl.AdminServiceImpl;
import main.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by pengweijie on 2018/12/4
 */
@WebServlet("/adminLookUser")
public class AdminLookUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminService adminService = new AdminServiceImpl();
        List<User> userList = new ArrayList<User>();
        userList = adminService.getAllUser();
        HttpSession session = req.getSession();
        session.setAttribute("userList", userList);
        req.getRequestDispatcher(req.getContextPath() + "jsp/admin/userList_admin.jsp").forward(req, resp);
    }
}
