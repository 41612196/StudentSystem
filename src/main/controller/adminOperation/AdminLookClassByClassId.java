package main.controller.adminOperation;

import main.dao.ClassesDao;
import main.dao.StudentDao;
import main.dao.daoImpl.ClassesDaoImpl;
import main.dao.daoImpl.StudentDaoImpl;
import main.vo.Classes;
import main.vo.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by pengweijie on 2018/12/4
 */
@WebServlet("/adminLookClassByClassId")
public class AdminLookClassByClassId extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Classes> classesList = new ArrayList<Classes>();
        ClassesDao classesDao = new ClassesDaoImpl();
        int classId =Integer.parseInt(req.getParameter("selectClassId")) ;

        System.out.println("选的"+classId);
        Classes classes = classesDao.selectClassByClassId(classId);

        if (classes != null) {
            System.out.println("找到的是："+classes);
            classesList.add(classes);
        }

        HttpSession session = req.getSession();
        session.setAttribute("classesList", classesList);
        req.getRequestDispatcher(req.getContextPath() + "/jsp/admin/classesList_admin.jsp").forward(req, resp);

    }
}
