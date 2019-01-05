package main.controller.Filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Create by pengweijie on 2018/11/18
 */
public class AuthorityFilter implements Filter {
    public void destory() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;
        HttpSession session=request.getSession();
        String superuser = (String) session.getAttribute("superuser");
        if (superuser == null) {
            response.sendRedirect(request.getContextPath() + "/authority.html");
        } else {
            if (superuser.equals("3")) {
                request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/admin/admin.html").forward(request,response);
            }else {
                response.sendRedirect(request.getContextPath() + "/ok.html");
            }
        }
    }

    public void init(FilterConfig args0) throws ServletException{

    }


}
