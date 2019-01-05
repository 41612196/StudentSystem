package main.controller.adminOperation;

import main.service.AdminService;
import main.service.serviceImpl.AdminServiceImpl;
import main.vo.Classes;
import main.vo.Course;
import main.vo.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by pengweijie on 2018/12/6
 */
@WebServlet("/getInfo")
public class GetInformation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Classes> classesList = new ArrayList<Classes>();
        List<Course> courseList = new ArrayList<Course>();
        List<Teacher> teacherList = new ArrayList<Teacher>();
        AdminService adminService = new AdminServiceImpl();
        classesList = adminService.getAllClasses();
        courseList = adminService.getAllCourse();
        teacherList = adminService.getAllTeacher();

        req.setAttribute("classesList", classesList);
        req.setAttribute("courseList", courseList);
        req.setAttribute("teacherList", teacherList);
        System.out.println(classesList);
        System.out.println(courseList);
        System.out.println(teacherList);
        req.getRequestDispatcher(req.getContextPath() + "jsp/admin/arrangeCourse.jsp").forward(req, resp);

    }
}
