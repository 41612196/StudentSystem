package main.controller.adminOperation;

import main.dao.StudentDao;
import main.dao.TeacherDao;
import main.dao.daoImpl.StudentDaoImpl;
import main.dao.daoImpl.TeacherDaoImpl;
import main.service.AdminService;
import main.service.TeacherService;
import main.service.serviceImpl.AdminServiceImpl;
import main.service.serviceImpl.TeacherServiceImpl;
import main.vo.Student;
import main.vo.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Create by pengweijie on 2018/12/5
 */
@WebServlet("/adminLookTeacher")
public class AdminLookTeacher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Teacher> teacherList = null;
        AdminService adminService = new AdminServiceImpl();
        teacherList = adminService.getAllTeacher();
        System.out.println("学生列表："+teacherList);
        HttpSession session = req.getSession();
        session.setAttribute("teacherList", teacherList);
        req.getRequestDispatcher(req.getContextPath() + "/jsp/admin/teacherList_admin.jsp").forward(req, resp);
    }
}
