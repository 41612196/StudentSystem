package main.controller.adminOperation;

import main.service.AdminService;
import main.service.serviceImpl.AdminServiceImpl;
import main.vo.Student;
import main.vo.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by pengweijie on 2018/12/5
 */
@WebServlet("/addTeacher")
public class AddTeacher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int teacherId = Integer.parseInt(req.getParameter("teacherId"));
        String teacherName = req.getParameter("teacherName");
        String sex = req.getParameter("sex");
        String college = req.getParameter("college");
        String professionalTitle = req.getParameter("professionalTitle");
        String degree = req.getParameter("degree");
        Teacher teacher = new Teacher(teacherId, teacherName, sex, college, professionalTitle, degree);
        AdminService adminService = new AdminServiceImpl();
        if (adminService.addTeacher(teacher)) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("1");
        }


    }
}
