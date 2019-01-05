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
@WebServlet("/deleteArrangedCourse")
public class DeleteArrangedCourse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int classId = Integer.parseInt(req.getParameter("classId"));
        int  courseId = Integer.parseInt(req.getParameter("courseId"));
        int teacherId = Integer.parseInt(req.getParameter("teacherId"));
        AdminService adminService = new AdminServiceImpl();

        if (adminService.deleteArrangedCourse(classId, courseId, teacherId)) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("1");
        }

    }

}
