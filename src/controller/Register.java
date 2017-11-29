package controller;

import model.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by GaryMao on 11/26/2017.
 */
@WebServlet(urlPatterns = {"/register"}, name = "Register", loadOnStartup = 1,
            initParams = {@WebInitParam(name = "SUCCESS_VIEW", value = "success.jsp"),
                          @WebInitParam(name = "ERROR_VIEW", value = "error.jsp")})
public class Register extends HttpServlet {
    private String SUCCESS_VIEW;
    private String ERROR_VIEW;

    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
        ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String usersname = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmedPasswd = request.getParameter("confirmedPasswd");

        UserService userService = (UserService)getServletContext().getAttribute("userService");

        List<String> errors = new ArrayList<>();
        if (isInvalidEmail(email)){
            errors.add("未填写邮件或邮件格式不正确");
        }
        if (userService.isInvalidUsername(usersname)){
            errors.add("用户名为空或已存在");
        }
        if (isInvalidPassWord(password, confirmedPasswd)){
            errors.add("请确认密码符合格式并在此确认密码");
        }
        String resultPage = ERROR_VIEW;
        if (!errors.isEmpty()){
            request.setAttribute("errors", errors);
        } else {
            resultPage = SUCCESS_VIEW;
            userService.createUserData(email, usersname, password);
        }

        request.getRequestDispatcher(resultPage).forward(request,response);
    }

    private boolean isInvalidEmail (String email){
        return email == null || !email.matches("^[_a-z0-9-]+([.]" + "[_a-z0-9-]+)*@[a-z0-9-]+([.][_a-z0-9-]+)*$");
    }

    private boolean isInvalidPassWord (String password, String confirmedPasswd){
        return password == null || password.length() < 6 || password.length() > 16 ||
                !password.equals(confirmedPasswd);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
