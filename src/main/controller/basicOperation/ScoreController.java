package main.controller.basicOperation;

import main.service.ScoreService;
import main.service.serviceImpl.ScoreServiceImpl;
import main.vo.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by pengweijie on 2018/11/27
 */
@WebServlet("/ScoreController")
public class ScoreController extends HttpServlet {
    private ScoreService scoreService = new ScoreServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPage = req.getParameter("currentPage");
        Page page = null;
        try {
            page = scoreService.pageScores(currentPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("page", page);
        System.out.println(page);

        req.getRequestDispatcher("/page.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
