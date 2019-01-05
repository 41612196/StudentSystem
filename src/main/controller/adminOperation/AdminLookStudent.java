package main.controller.adminOperation;

import main.dao.StudentDao;
import main.dao.daoImpl.StudentDaoImpl;
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
@WebServlet("/selectStudent")
public class AdminLookStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = null;
        StudentDao studentDao = new StudentDaoImpl();
        studentList = studentDao.findAll();
        System.out.println("学生列表："+studentList);
        HttpSession session = req.getSession();
        session.setAttribute("studentList", studentList);
        req.getRequestDispatcher(req.getContextPath() + "/jsp/admin/studentList_admin.jsp").forward(req, resp);

    }
}
