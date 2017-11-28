package controller;

import model.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;

/**
 * Created by GaryMao on 11/27/2017.
 */
@WebServlet(name = "Delete", urlPatterns = {"/delete"},
            initParams = {@WebInitParam(name = "SUCCESS_VIEW", value = "member")})
public class Delete extends HttpServlet {
    private String SUCCESS_VIEW;

    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = (UserService) getServletContext().getAttribute("userService");
        String username = (String)request.getSession().getAttribute("username");
        String date = request.getParameter("date");
        userService.deleteMessage(username, date);
        response.sendRedirect(SUCCESS_VIEW);
    }
}
