package controller;

import model.Account;
import model.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * Created by GaryMao on 11/27/2017.
 */
@WebServlet(name = "Login", urlPatterns = {"/login"},
        initParams = {@WebInitParam(name = "SUCCESS_VIEW", value = "sendmessage"),
                      @WebInitParam(name = "ERROR_VIEW", value = "index.jsp")})
public class Login extends HttpServlet {
    private String SUCCESS_VIEW;
    private String ERROR_VIEW;

    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
        ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        String page;

        UserService userService = (UserService)getServletContext().getAttribute("userService");

        if (userService.checkLogin(account)){
            request.getSession().setAttribute("username", username);
            page = SUCCESS_VIEW;
        } else {
            request.setAttribute("error", "名称或密码错误");
            page = ERROR_VIEW;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
