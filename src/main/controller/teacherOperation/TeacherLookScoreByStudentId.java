package main.controller.teacherOperation;

import main.dao.CourseDao;
import main.dao.TeacherDao;
import main.dao.daoImpl.CourseDaoImpl;
import main.dao.daoImpl.TeacherDaoImpl;
import main.service.TeacherService;
import main.service.serviceImpl.TeacherServiceImpl;
import main.vo.Course;
import main.vo.Score;

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
 * Create by pengweijie on 2018/11/30
 */
@WebServlet("/teacherLookScoreByStudentId")
public class TeacherLookScoreByStudentId extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourseDao courseDao = new CourseDaoImpl();
        TeacherService teacherService = new TeacherServiceImpl();
        TeacherDao teacherDao = new TeacherDaoImpl();
        String selectStudentId_Look = null;
        List<Score> scoreListAllStudent = new ArrayList<Score>();//用来存某个教师的所有学生的成绩
        List<Score> scoreList = new ArrayList<Score>();//用来存某个教师指定学生的成绩
        HttpSession session = req.getSession();

        if (!req.getParameter("selectStudentId").equals("")) {
            selectStudentId_Look = req.getParameter("selectStudentId");
            System.out.println("学号分类：" + selectStudentId_Look);

            String teacherId = (String) session.getAttribute("username");
            System.out.println("教师号：" + teacherId);


            System.out.println("选的学号是：" + selectStudentId_Look);

            scoreListAllStudent = teacherService.queryScoreByTeacherIdFromScore(Integer.parseInt(teacherId));//得到该教师的所有学生的成绩
            for (Score score :
                    scoreListAllStudent) {
                if (score.getStudentId() == Integer.parseInt(selectStudentId_Look)) {
                    scoreList.add(score);
                }
            }
            session.setAttribute("scoreList", scoreList);
            System.out.println("该门课成绩：" + scoreList);
            req.getRequestDispatcher(req.getContextPath() + "jsp/teacher/studentScoreList_teacher.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher(req.getContextPath() + "jsp/teacher/studentScoreList_teacher.jsp").forward(req, resp);
        }

    }
}
