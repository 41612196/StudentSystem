package main.controller.teacherOperation;

import main.dao.StudentDao;
import main.dao.TeacherDao;
import main.dao.daoImpl.StudentDaoImpl;
import main.dao.daoImpl.TeacherDaoImpl;
import main.vo.Student;
import main.vo.Teacher;

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
@WebServlet("/teacherInfo")
public class TeacherInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int teacherId = Integer.parseInt((String)session.getAttribute("username"));

        Teacher teacher = new Teacher();

        TeacherDao teacherDao = new TeacherDaoImpl();

        teacher = teacherDao.findTeacherById(teacherId);

        session.setAttribute("teacherInfo", teacher);
        req.getRequestDispatcher(req.getContextPath() + "jsp/teacher/teacherInfo.jsp").forward(req, resp);
    }
}
