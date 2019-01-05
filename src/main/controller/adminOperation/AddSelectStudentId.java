package main.controller.adminOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Create by pengweijie on 2018/12/4
 */
@WebServlet("/addSelectStudentId")
public class AddSelectStudentId extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt((String)req.getParameter("studentId"));
        HttpSession session = req.getSession();
        session.setAttribute("studentId", studentId);

        req.getRequestDispatcher(req.getContextPath() + "/jsp/admin/studentList_admin.jsp").forward(req, resp);

    }
}
