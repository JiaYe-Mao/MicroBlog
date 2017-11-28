package controller;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by GaryMao on 11/27/2017.
 */
@WebServlet(name = "Logout", urlPatterns = {"/logout"},
        initParams = {@WebInitParam(name = "LOGIN_VIEW", value = "index.jsp")})
public class Logout extends HttpServlet {
    private String LOGIN_VIEW;

    @Override
    public void init() throws ServletException {
        this.LOGIN_VIEW = getServletConfig().getInitParameter("LOGIN_VIEW");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(LOGIN_VIEW);
    }
}
