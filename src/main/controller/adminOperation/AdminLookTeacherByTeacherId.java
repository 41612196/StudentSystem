package main.controller.adminOperation;

import main.dao.StudentDao;
import main.dao.daoImpl.StudentDaoImpl;
import main.service.AdminService;
import main.service.TeacherService;
import main.service.serviceImpl.AdminServiceImpl;
import main.vo.Student;
import main.vo.Teacher;

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
@WebServlet("/adminLookTeacherByTeacherId")
public class AdminLookTeacherByTeacherId extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Teacher> teacherList = new ArrayList<Teacher>();
        AdminService adminService = new AdminServiceImpl();
        Teacher teacher = new Teacher();
        int teacherId = Integer.parseInt(req.getParameter("selectTeacherId")) ;
        teacher = adminService.findTeacherByTeacherId(teacherId);
        System.out.println("查到的老师：+++"+teacher);
        if (teacher.getTeacherId() == 0) {

        }else {
            teacherList.add(teacher);
        }


        HttpSession session = req.getSession();
        session.setAttribute("teacherList", teacherList);
        System.out.println("查到的教师：" + teacherList);
        req.getRequestDispatcher(req.getContextPath() + "/jsp/admin/teacherList_admin.jsp").forward(req, resp);

    }
}
