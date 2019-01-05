package main.controller.adminOperation;

import main.dao.StudentDao;
import main.dao.daoImpl.StudentDaoImpl;
import main.service.AdminService;
import main.service.serviceImpl.AdminServiceImpl;
import main.vo.Classes;
import main.vo.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Create by pengweijie on 2018/12/4
 */
@WebServlet("/adminLookClasses")
public class AdminLookClasses extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Classes> classesList = null;
        AdminService adminService = new AdminServiceImpl();
        classesList = adminService.getAllClasses();

        HttpSession session = req.getSession();
        session.setAttribute("classesList", classesList);
        req.getRequestDispatcher(req.getContextPath() + "/jsp/admin/classesList_admin.jsp").forward(req, resp);

    }
}
