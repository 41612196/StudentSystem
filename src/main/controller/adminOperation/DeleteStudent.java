package main.controller.adminOperation;

import main.dao.StudentDao;
import main.dao.daoImpl.StudentDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by pengweijie on 2018/12/4
 */
@WebServlet("/deleteStudent")
public class DeleteStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt((String) req.getParameter("studentId"));
        System.out.println("要删除的是" + studentId);
        StudentDao studentDao = new StudentDaoImpl();
        if(studentDao.deleteStudentById(studentId)){
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("1");
        }

    }
}
