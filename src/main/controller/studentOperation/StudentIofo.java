package main.controller.studentOperation;

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

/**
 * Create by pengweijie on 2018/12/2
 */
@WebServlet("/studentInfo")
public class StudentIofo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int studentId = Integer.parseInt((String)session.getAttribute("username"));

        System.out.println("用户名"+studentId);

        Student student = new Student();

        StudentDao studentDao = new StudentDaoImpl();

        student = studentDao.findStudentById(studentId);
        System.out.println("个人信息：" + student);

        session.setAttribute("studentInfo", student);
        req.getRequestDispatcher(req.getContextPath() + "jsp/student/studentInfo.jsp").forward(req, resp);
    }
}
