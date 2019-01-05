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
 * Create by pengweijie on 2018/12/4
 */
@WebServlet("/deleteClass")
public class DeleteClass extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classesId = req.getParameter("classId");
        AdminService adminService = new AdminServiceImpl();
        System.out.println("删除的班级"+classesId);

        if (adminService.deleteClass(Integer.parseInt(classesId))) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("1");
        } else {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("0");
        }

    }
}
