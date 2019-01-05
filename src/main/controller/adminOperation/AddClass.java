package main.controller.adminOperation;

import main.service.AdminService;
import main.service.serviceImpl.AdminServiceImpl;
import main.vo.Classes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by pengweijie on 2018/12/5
 */
@WebServlet("/addClass")
public class AddClass extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int classesId = Integer.parseInt(req.getParameter("classesId"));
        String classesName = req.getParameter("classesName");
        String ofCollege = req.getParameter("ofCollege");
        System.out.println("添加了："+classesName);
        AdminService adminService = new AdminServiceImpl();
        Classes classes = new Classes(classesId, classesName, ofCollege);
        if (adminService.addClass(classes)) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("1");
        }


    }
}
