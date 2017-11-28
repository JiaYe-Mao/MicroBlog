package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static consts.PageConst.*;


/**
 * Created by GaryMao on 11/26/2017.
 */
@WebServlet(urlPatterns = {"/register"}, name = "Register", loadOnStartup = 1)
public class Register extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String usersname = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmedPasswd = request.getParameter("confirmedPasswd");

        List<String> errors = new ArrayList<>();
        if (isInvalidEmail(email)){
            errors.add("未填写邮件或邮件格式不正确");
        }
        if (isInvalidUsername(usersname)){
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
            createUserData(email, usersname, password);
        }

        request.getRequestDispatcher(resultPage).forward(request,response);
    }

    private boolean isInvalidEmail (String email){
        return email == null || !email.matches("^[_a-z0-9-]+([.]" + "[_a-z0-9-]+)*@[a-z0-9-]+([.][_a-z0-9-]+)*$");
    }

    private boolean isInvalidUsername (String username){
        File file1 = new File(USERS);
        if (!file1.exists()){
            file1.mkdir();
        }
        for (String file : new File(USERS).list()){
            if(file.equals(username)){
                return true;
            }
        }
        return false;
    }

    private boolean isInvalidPassWord (String password, String confirmedPasswd){
        return password == null || password.length() < 6 || password.length() > 16 ||
                !password.equals(confirmedPasswd);
    }

    private void createUserData (String email, String username, String password) throws IOException {
        File userhome = new File(USERS + "/" + username);
        userhome.mkdir();
        BufferedWriter writer = new BufferedWriter(new FileWriter(userhome + "/profile"));
        writer.write(email + "\t" + password);
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
