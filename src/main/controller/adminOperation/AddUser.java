package main.controller.adminOperation;

import main.service.AdminService;
import main.service.serviceImpl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by pengweijie on 2018/12/5
 */
@WebServlet("/addUser")
public class AddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String superuser = req.getParameter("superuser");
        System.out.println("添加了："+username);
        AdminService adminService = new AdminServiceImpl();
        if(adminService.addUser(username, password, superuser)){
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("1");
        }

    }
}
