package main.controller.teacherOperation;

import main.dao.ScoreDao;
import main.dao.daoImpl.ScoreDaoImpl;
import main.service.TeacherService;
import main.service.serviceImpl.TeacherServiceImpl;
import main.vo.Score;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Create by pengweijie on 2018/11/30
 */
@WebServlet("/updateScore")
public class UpdateScore extends HttpServlet {
    ScoreDao scoreDao = new ScoreDaoImpl();
    TeacherService teacherService = new TeacherServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("111111111111111111111111");


        HttpSession session = req.getSession();
        System.out.println("学号："+req.getParameter("studentId"));

        int studentId = Integer.parseInt(req.getParameter("studentId"));
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        int grade = Integer.parseInt(req.getParameter("grade"));

        System.out.println("修改分数"+grade);

        if(teacherService.updateScoreByStudentIdAndCourseId(studentId, courseId, grade)){

                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().write("1");

        }


    }


}
