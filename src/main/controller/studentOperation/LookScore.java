package main.controller.studentOperation;

import main.service.StudentService;
import main.service.serviceImpl.StudentServiceImpl;
import main.vo.Score;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Create by pengweijie on 2018/11/26
 */
@WebServlet("/lookScore")
public class LookScore extends HttpServlet {

   private StudentService studentService = new StudentServiceImpl();

    public void init() throws ServletException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        System.out.println(username);

        int studentId = Integer.parseInt(username);
        List<Score> scoreList = studentService.queryScore(studentId);
        System.out.println(scoreList);
        session.setAttribute("scoreList", scoreList);
        request.getRequestDispatcher(request.getContextPath() + "jsp/student/studentScoreList.jsp").forward(request, response);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
