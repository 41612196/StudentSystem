package main.controller.teacherOperation;

import main.dao.ScoreDao;
import main.dao.daoImpl.ScoreDaoImpl;
import main.service.StudentService;
import main.service.serviceImpl.StudentServiceImpl;
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
 * Create by pengweijie on 2018/11/29
 */
@WebServlet("/teacherLookScore")
public class TeacherLookScore extends HttpServlet{

    private StudentService studentService = new StudentServiceImpl();

    public void init() throws ServletException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<String> studentIdList = new ArrayList<String>();//该教师某门课的学生学号
        List<Score> scoreList = new ArrayList<Score>();
        Score score = null;
        ScoreDao scoreDao = new ScoreDaoImpl();

        studentIdList = (List<String>)session.getAttribute("studentIdList");
        Course course = (Course)session.getAttribute("selectCourse");
        for (String studentId:
             studentIdList) {
            score = scoreDao.queryScoreByStudentIdAndCourseId(Integer.parseInt(studentId), course.getCourseId());
            scoreList.add(score);
        }
        String username = (String) session.getAttribute("username");
        System.out.println("教师"+username+"该门课的学生："+studentIdList);

        session.setAttribute("scoreList", scoreList);
        request.getRequestDispatcher(request.getContextPath() + "jsp/teacher/studentScoreList_teacher.jsp").forward(request, response);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
