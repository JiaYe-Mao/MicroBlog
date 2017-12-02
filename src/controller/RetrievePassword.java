package controller;

import model.Account;
import model.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RetrievePassword", urlPatterns = {"password"},
                initParams = {@WebInitParam(name = "SUCCESS_VIEW", value = "ok.jsp"),
                            @WebInitParam(name = "ERROR_VIEW", value = "forget.jsp")})
public class RetrievePassword extends HttpServlet {
    private String SUCCESS_VIEW;
    private String ERROR_VIEW;

    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getInitParameter("SUCCESS_VIEW");
        ERROR_VIEW = getInitParameter("ERROR_VIEW");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("name");
        String email = request.getParameter("email");

        Account account = new Account();
        account.setUsername(username);
        account.setEmail(email);
        String page=null;
        UserService userService = (UserService)getServletContext().getAttribute("userService");
        if (userService.sendPasswordTo(account)){
            page = SUCCESS_VIEW;
            request.setAttribute("name", username);
            request.setAttribute("email", email);
        } else {
            request.setAttribute("error", "用户名称不存在或邮件有误!");
        }
        request.getRequestDispatcher(page).forward(request, response);
    }


}
