package main.controller.Filters;

import javax.servlet.*;
import java.io.IOException;



/**
 * Create by pengweijie on 2018/11/30
 */
public class ConvertEncoding implements Filter {

    private String encoding;
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding(encoding);//设置请求编码
        resp.setContentType("text/html;charset="+encoding);//设置响应编码
        chain.doFilter(req,resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.encoding = config.getInitParameter("encoding");//读取默认编码
    }

}