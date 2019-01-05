package main.controller.teacherOperation;

import main.dao.ScoreDao;
import main.dao.daoImpl.ScoreDaoImpl;
import main.vo.Score;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by pengweijie on 2018/12/4
 */
@WebServlet("/deleteScore")
public class deleteScore extends HttpServlet {
//    ScoreDao scoreDao = new ScoreDaoImpl();
//    public boolean

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("删除..................");
        String studentId = req.getParameter("studentId");
        String courseId = req.getParameter("courseId");
        System.out.println("str" + studentId + "课程Id" + courseId);

        ScoreDao scoreDao = new ScoreDaoImpl();
        Score score = scoreDao.queryScoreByStudentIdAndCourseId(Integer.parseInt(studentId), Integer.parseInt(courseId));
        if (scoreDao.deleteScore(score)) {

            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("1");
        }
    }
}
