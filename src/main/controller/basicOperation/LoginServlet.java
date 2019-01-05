package main.controller.basicOperation;

import main.service.LoginService;
import main.service.serviceImpl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Create by pengweijie on 2018/11/17
 */

public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();

    public void init() throws ServletException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        System.out.println("jjjjjjjjjjjjjjj"+request.getParameter("lll"));

        String str = loginService.login(username, password);
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("superuser", str);
        if (str != null) {
            String s=null;
            switch (str){
                case "1":
                    s = "student";
                    break;
                case "2":
                    s = "teacher";
                    break;
                case "3":
                    s = "admin";
                    break;

            }
            String path ="/jsp/"+s+"/framework" + s + ".jsp";
            request.getRequestDispatcher(request.getContextPath() +path).forward(request,response);
        } else {

            response.sendRedirect(request.getContextPath() + "/index.jsp");

        }

    }


}
