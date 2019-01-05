package main.controller.adminOperation;

import main.service.AdminService;
import main.service.serviceImpl.AdminServiceImpl;
import main.vo.Course;
import main.vo.Score;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by pengweijie on 2018/12/5
 */
@WebServlet("/addCourse")
public class AddCourse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int  courseId = Integer.parseInt(req.getParameter("courseId"));
        System.out.println("课程id："+courseId);
        String courseName = req.getParameter("courseName");
        System.out.println("课程name："+courseName);
        float credit = Float.parseFloat(req.getParameter("credit"));
        System.out.println("课程name："+courseName);
        Course course = new Course(courseId, courseName, credit);
        System.out.println("课程："+credit);
        System.out.println("添加的课程：" + course);
        AdminService adminService = new AdminServiceImpl();
        if(adminService.addCourse(course)){
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("1");
        }

    }
}
