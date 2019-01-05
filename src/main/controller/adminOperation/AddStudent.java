package main.controller.adminOperation;

import main.dao.ClassesDao;
import main.dao.daoImpl.ClassesDaoImpl;
import main.service.AdminService;
import main.service.serviceImpl.AdminServiceImpl;
import main.vo.Classes;
import main.vo.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by pengweijie on 2018/12/5
 */
@WebServlet("/addStudent")
public class AddStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        String studentName = req.getParameter("studentName");
        String sex = req.getParameter("sex");
        String major = req.getParameter("major");
        String tel = req.getParameter("tel");
        String clazz = req.getParameter("classes");
        int age = Integer.parseInt(req.getParameter("age"));
        String address = req.getParameter("address");
        int yearSchool = Integer.parseInt(req.getParameter("yearSchool"));

        String preClassId =String.valueOf(yearSchool - 2000);
        System.out.println("班号前缀："+preClassId);

        String sufClassId=null ;
        switch (clazz) {
            case "软工一班":
                sufClassId = "01";break;
            case "软工二班":
                sufClassId = "02";break;
            case "信息管理与信息系统":
                sufClassId = "03";break;
            case "计算机科学与技术（创新实验班）":
                sufClassId = "04";break;
            case "计科一班":
                sufClassId = "05";break;
            case "计科二班":
                sufClassId = "06";break;
                default:
                    break;

        }
        ClassesDao classesDao = new ClassesDaoImpl();
        int classId = Integer.parseInt(preClassId + sufClassId);
        Classes classes = classesDao.selectClassByClassId(classId);
        AdminService adminService = new AdminServiceImpl();
        Student student = new Student(studentId, studentName,sex, major, age, yearSchool, tel, address);
        System.out.println("添加的学生" + student);

        if (adminService.addStudent(student) && adminService.addStudentToClass(student,classes)) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("1");
        }


    }
}
